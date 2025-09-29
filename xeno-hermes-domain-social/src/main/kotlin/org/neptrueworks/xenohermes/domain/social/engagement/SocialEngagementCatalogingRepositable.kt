package org.neptrueworks.xenohermes.domain.social.engagement

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public interface SocialEngagementCatalogingRepositable : AggregateRepositable<SocialEngagementCatalogingAggregateRoot> {
    public abstract fun fetchByIdentifier(engager: SocialEngagementEngager, engagee: SocialEngagementEngagee): SocialEngagementCatalogingAggregateRoot
}