package org.neptrueworks.xenohermes.domain.social.invitation.events

import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.commands.AcceptSocialInvitationCommand
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAccepter
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAgent

public data class SocialInvitationAcceptedEvent private constructor(
    val invitationId: SocialInvitationIdentifier,
    val agent: SocialInvitationAgent,
    val accepter: SocialInvitationAccepter,
) : SocialInvitationEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: AcceptSocialInvitationCommand, agent: SocialInvitationAgent) = SocialInvitationAcceptedEvent(
            invitationId = command.invitationId,
            agent = agent,
            accepter = command.accepter,
        )
    }
}
