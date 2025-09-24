package org.neptrueworks.xenohermes.domain.social.engagement.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class SocialEngagementThreshold(val maximumEngagement: UInt);

public inline fun SocialEngagementThreshold.isMaximumEngagementExceeds(engagement: SocialEngagementCount) =
    this.maximumEngagement <= engagement.currentEngagement;