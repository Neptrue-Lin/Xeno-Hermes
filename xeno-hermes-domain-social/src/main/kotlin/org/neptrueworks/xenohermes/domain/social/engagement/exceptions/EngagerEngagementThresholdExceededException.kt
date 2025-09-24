package org.neptrueworks.xenohermes.domain.social.engagement.exceptions

import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementThreshold

public final class EngagerEngagementThresholdExceededException internal constructor(
    val engager: SocialEngagementEngager,
    val engagerEngagementThreshold: SocialEngagementThreshold
) : SocialEngagementException()