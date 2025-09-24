package org.neptrueworks.xenohermes.domain.social.engagement.exceptions

import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public final class EngageeAlreadyEngagedException internal constructor(
    val engager: SocialEngagementEngager,
    val engagee: SocialEngagementEngagee
) : SocialEngagementException()