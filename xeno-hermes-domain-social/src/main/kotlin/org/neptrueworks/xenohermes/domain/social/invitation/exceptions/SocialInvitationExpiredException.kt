package org.neptrueworks.xenohermes.domain.social.invitation.exceptions

import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAgent
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationExpiryPeriod

public final class SocialInvitationExpiredException internal constructor(
    val invitationId: SocialInvitationIdentifier,
    val agent: SocialInvitationAgent,
    val expiredAt: SocialInvitationExpiryPeriod
) : SocialInvitationException()
