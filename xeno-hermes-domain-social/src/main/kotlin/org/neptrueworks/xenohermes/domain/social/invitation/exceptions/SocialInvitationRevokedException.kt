package org.neptrueworks.xenohermes.domain.social.invitation.exceptions

import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAccepter
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAgent

public final class SocialInvitationRevokedException internal constructor(
    val invitationId: SocialInvitationIdentifier,
    val accepter: SocialInvitationAccepter,
    val agent: SocialInvitationAgent,
) : SocialInvitationException()
