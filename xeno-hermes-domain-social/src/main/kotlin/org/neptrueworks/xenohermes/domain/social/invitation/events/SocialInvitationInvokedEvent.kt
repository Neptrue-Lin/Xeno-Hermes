package org.neptrueworks.xenohermes.domain.social.invitation.events

import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.commands.AcceptSocialInvitationCommand
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAccepter

public data class SocialInvitationInvokedEvent private constructor(
    val invitationId: SocialInvitationIdentifier,
    val accepter: SocialInvitationAccepter,
) : SocialInvitationEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: AcceptSocialInvitationCommand) = SocialInvitationInvokedEvent(
            invitationId = command.invitationId,
            accepter = command.accepter,
        )
    }
}
