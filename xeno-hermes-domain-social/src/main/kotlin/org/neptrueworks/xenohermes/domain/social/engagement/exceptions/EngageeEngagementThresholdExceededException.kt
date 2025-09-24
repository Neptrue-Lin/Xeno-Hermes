package org.neptrueworks.xenohermes.domain.social.engagement.exceptions

import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementThreshold

public final class EngageeEngagementThresholdExceededException internal constructor(
    val engagee: SocialEngagementEngagee,
    val engageeEngagementThreshold: SocialEngagementThreshold
) : SocialEngagementException()