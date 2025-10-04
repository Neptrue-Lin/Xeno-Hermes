package org.neptrueworks.xenohermes.contract.social.engagement

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.KNonNullPropExpression
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.notExists
import org.babyfish.jimmer.sql.kt.ast.table.KNonNullTable
import org.neptrueworks.xenohermes.contract.social.engagement.params.SocialEngagementCataloger
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Repository

@Repository
internal final class SocialEngagementCatalogingRepository(
    private val kSqlClient: KSqlClient,
    private val saver: SocialEngagementCatalogingSaver,
) : SocialEngagementCatalogingRepositable {
    override fun fetchByIdentifier(engager: SocialEngagementEngager, engagee: SocialEngagementEngagee): SocialEngagementCatalogingAggregateRoot {
        val engagement = this.kSqlClient.findById(SocialEngager::class, engager);
        val nonengagement = this.kSqlClient.createQuery(SocialEngager::class) {
            val notEngaged = notExists(wildSubQuery(SocialEngager::class) {
                where(table.engager eq engager)
                where(table.asTableEx().engaged.engagee eq engagee)
            })
            where(table.engagee eq engagee)
            where(notEngaged)
            select(table)
        }.fetchOneOrNull();

        assert(engagement != null);
        return SocialEngagementCatalogingAggregator(SocialEngagementCataloger(engagement!!, nonengagement), engagement);
    }

    override fun reposit(aggregateRoot: SocialEngagementCatalogingAggregateRoot) {
        this.saver.save(aggregateRoot as SocialEngagementCatalogingAggregator);
    }

    private inline val KNonNullTable<SocialEngager>.engagee get() = this.engager;
    private infix fun KNonNullPropExpression<SocialEngagementEngager>.eq(engagee: SocialEngagementEngagee) =
        this eq SocialEngagementEngager(engagee.identifier);
}