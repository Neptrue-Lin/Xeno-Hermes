package org.neptrueworks.xenohermes.contract.social.engagement

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRestrictionAggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRestrictionRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Repository

@Repository
internal final class SocialEngagementRestrictionRepository(
    private val kSqlClient: KSqlClient
) : SocialEngagementRestrictionRepositable {
    override fun fetchByIdentifier(engager: SocialEngagementEngager): SocialEngagementRestrictionAggregateRoot {
        return this.kSqlClient.findById(SocialEngager::class, engager)
            .run(::SocialEngagementRestrictionAggregator)
    }
    override fun reposit(aggregateRoot: SocialEngagementRestrictionAggregateRoot) {
        val aggregator = aggregateRoot as SocialEngagementRestrictionAggregator;
        this.kSqlClient.saveCommand(aggregator.resolve(), SaveMode.UPDATE_ONLY).execute();
    }
}