package org.neptrueworks.xenohermes.domain.social.invitation.policies

import org.neptrueworks.xenohermes.domain.common.event.DomainEventHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.events.InterlocutorDisengagedEvent
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationResponseRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.commands.RevokeSocialInvitationCommand
import org.neptrueworks.xenohermes.domain.social.invitation.commands.RevokeSocialInvitationCommandHandler
import org.neptrueworks.xenohermes.domain.social.invitation.events.DisengagedSocialInvitationRevokedEvent
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationIssuer
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevocationDateTime
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevoker
import org.springframework.stereotype.Service

@Service
public final class DisengagedSocialInvitationRevocationPolicy(
    private val commandHandler: RevokeSocialInvitationCommandHandler,
    private val invitationResponseRepository: SocialInvitationResponseRepositable,
    private val eventTrigger: DomainEventRaiseable<DisengagedSocialInvitationRevokedEvent>
) : DomainEventHandler<InterlocutorDisengagedEvent>() {
    public override fun handle(event: InterlocutorDisengagedEvent) {
        val issuer = SocialInvitationIssuer(event.disengager.identifier);
        this.invitationResponseRepository.fetchAllPending(issuer).forEach { invitation ->
            val revoker = SocialInvitationRevoker(invitation.agent.identifier);
            val revocationDateTime = SocialInvitationRevocationDateTime(event.disengagementDateTime.disengagedAt);
            this.commandHandler.handle(RevokeSocialInvitationCommand(invitation.invitationId, revoker, revocationDateTime)); 
        }
        this.eventTrigger.raise(DisengagedSocialInvitationRevokedEvent.initialize(event));
    }
}
