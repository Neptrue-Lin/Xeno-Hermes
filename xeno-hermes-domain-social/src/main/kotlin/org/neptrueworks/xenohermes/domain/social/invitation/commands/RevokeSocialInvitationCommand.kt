package org.neptrueworks.xenohermes.domain.social.invitation.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.events.SocialInvitationEvent
import org.neptrueworks.xenohermes.domain.social.invitation.events.SocialInvitationRevokedEvent
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevocationDateTime
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevoker
import org.springframework.stereotype.Service

public data class RevokeSocialInvitationCommand(
    val invitationId: SocialInvitationIdentifier,
    val revoker: SocialInvitationRevoker,
    val revocationDateTime: SocialInvitationRevocationDateTime
) : SocialInvitationCommand

@Service
public final class RevokeSocialInvitationCommandHandler(
    private val repository: SocialInvitationRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialInvitationEvent>
) : CommandHandler<RevokeSocialInvitationCommand>() {
    public override fun handle(command: RevokeSocialInvitationCommand) {
        val invitation = this.repository.fetchByIdentifier(command.invitationId);
        invitation.revokeSocialInvitation(command);
        this.eventTrigger.raise(SocialInvitationRevokedEvent.initialize(command, invitation.agent));
        this.repository.reposit(invitation);
    }
}
