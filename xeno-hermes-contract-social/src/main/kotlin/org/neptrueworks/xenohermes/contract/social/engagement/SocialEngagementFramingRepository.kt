package org.neptrueworks.xenohermes.contract.social.engagement

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.notExists
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementFramingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementFramingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Repository

@Repository
internal final class SocialEngagementFramingRepository(
    private val jimmerClient: KSqlClient
) : SocialEngagementFramingRepositable {
    override fun reposit(aggregateRoot: SocialEngagementFramingAggregateRoot) {
        val aggregator = aggregateRoot as SocialEngagementFramingAggregator;
        this.jimmerClient.saveCommand(aggregator.resolve(), SaveMode.INSERT_ONLY).execute();
    }
}