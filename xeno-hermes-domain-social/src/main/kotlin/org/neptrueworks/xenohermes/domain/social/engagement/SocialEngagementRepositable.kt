package org.neptrueworks.xenohermes.domain.social.engagement

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public interface SocialEngagementRepositable : AggregateRepositable<SocialEngagementAggregateRoot> {
    public abstract fun fetchByIdentifier(engager: SocialEngagementEngager): SocialEngagementAggregateRoot
}