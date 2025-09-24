package org.neptrueworks.xenohermes.domain.social.invitation.events

import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.commands.RevokeSocialInvitationCommand
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAgent
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevoker

public data class SocialInvitationRevokedEvent private constructor(
    val invitationId: SocialInvitationIdentifier,
    val revoker: SocialInvitationRevoker,
    val agent: SocialInvitationAgent,
) : SocialInvitationEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: RevokeSocialInvitationCommand, agent: SocialInvitationAgent) = SocialInvitationRevokedEvent(
            invitationId = command.invitationId,
            revoker = command.revoker,
            agent = agent,
        )
    }
}
