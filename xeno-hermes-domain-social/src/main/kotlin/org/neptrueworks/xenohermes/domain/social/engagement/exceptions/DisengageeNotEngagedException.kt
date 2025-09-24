package org.neptrueworks.xenohermes.domain.social.engagement.exceptions

import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public final class DisengageeNotEngagedException internal constructor(
    val disengager: SocialEngagementEngager
) : SocialEngagementException()