package org.neptrueworks.xenohermes.domain.social.engagement.exceptions

import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager


public final class InvitationEngagementAlreadyPermittedException internal constructor(
    val engager: SocialEngagementEngager
) : SocialEngagementException()
