package org.neptrueworks.xenohermes.contract.social.blockage

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.KNonNullPropExpression
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.exists
import org.babyfish.jimmer.sql.kt.ast.expression.notExists
import org.babyfish.jimmer.sql.kt.ast.table.KNonNullTable
import org.neptrueworks.xenohermes.contract.social.blockage.params.SocialBlockageCataloger
import org.neptrueworks.xenohermes.contract.social.engagement.SocialEngager
import org.neptrueworks.xenohermes.contract.social.engagement.engaged
import org.neptrueworks.xenohermes.contract.social.engagement.engager
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Repository

@Repository
internal final class SocialBlockageCatalogingRepository(
    private val kSqlClient: KSqlClient,
) : SocialBlockageCatalogingRepositable {
    override fun fetchByIdentifier(blocker: SocialBlockageBlocker, blockee: SocialBlockageBlockee): SocialBlockageCatalogingAggregateRoot {
        val blockage = this.kSqlClient.findById(SocialBlocker::class, blocker);
        assert(blockage != null);

        val nonblockage = this.kSqlClient.createQuery(SocialBlocker::class) {
            where(table.blockee eq blockee)
            where(notExists(wildSubQuery(SocialBlocker::class) {
                where(table.blocker eq blocker)
                where(table.asTableEx().blocked.blockee eq blockee)
            }))
            select(table)
        }.fetchOneOrNull();

        return SocialBlockageCatalogingAggregator(SocialBlockageCataloger(blockage!!, nonblockage), blockage);
    }

    override fun reposit(aggregateRoot: SocialBlockageCatalogingAggregateRoot) {
        val aggregator = aggregateRoot as SocialBlockageCatalogingAggregator;
        this.kSqlClient.saveCommand(aggregator.__resolve() as SocialBlocker, SaveMode.UPDATE_ONLY).execute();
    }
    
    private inline val KNonNullTable<SocialBlocker>.blockee get() = this.blocker;
    private infix fun KNonNullPropExpression<SocialBlockageBlocker>.eq(blockee: SocialBlockageBlockee) =
        this eq SocialBlockageBlocker(blockee.identifier);
}