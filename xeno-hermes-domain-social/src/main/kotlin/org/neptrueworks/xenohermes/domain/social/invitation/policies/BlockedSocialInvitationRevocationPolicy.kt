package org.neptrueworks.xenohermes.domain.social.invitation.policies

import org.neptrueworks.xenohermes.domain.common.event.DomainEventHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.blockage.events.InterlocutorBlockedEvent
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationResponseRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.commands.RevokeSocialInvitationCommand
import org.neptrueworks.xenohermes.domain.social.invitation.commands.RevokeSocialInvitationCommandHandler
import org.neptrueworks.xenohermes.domain.social.invitation.events.BlockedSocialInvitationRevokedEvent
import org.neptrueworks.xenohermes.domain.social.invitation.isInvalid
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAudience
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationIssuer
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevocationDateTime
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevoker
import org.springframework.stereotype.Service

@Service
public final class BlockedSocialInvitationRevocationPolicy(
    private val commandHandler: RevokeSocialInvitationCommandHandler,
    private val invitationResponseRepository: SocialInvitationResponseRepositable,
    private val eventTrigger: DomainEventRaiseable<BlockedSocialInvitationRevokedEvent>
) : DomainEventHandler<InterlocutorBlockedEvent>() {
    public override fun handle(event: InterlocutorBlockedEvent) {
        val issuer = SocialInvitationIssuer(event.blocker.identifier);
        val invitee = SocialInvitationAudience(event.blockee.identifier);
        
        val invitation = this.invitationResponseRepository.fetchPrevious(issuer, invitee);
        if (invitation.isInvalid(event.blockageDateTime.blockedAt))
            return; // TODO: Result ?

        val revoker = SocialInvitationRevoker(event.blocker.identifier);
        val revocationDateTime = SocialInvitationRevocationDateTime(event.blockageDateTime.blockedAt);
        this.commandHandler.handle(RevokeSocialInvitationCommand(invitation.invitationId, revoker, revocationDateTime));
        this.eventTrigger.raise(BlockedSocialInvitationRevokedEvent.initialize(event));
    }
}
