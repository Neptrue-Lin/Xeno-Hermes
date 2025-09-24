package org.neptrueworks.xenohermes.domain.social.invitation.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationFactory
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.events.SocialInvitationEvent
import org.neptrueworks.xenohermes.domain.social.invitation.events.SocialInvitationIssuedEvent
import org.neptrueworks.xenohermes.domain.social.invitation.params.*
import org.springframework.stereotype.Service

public data class IssueSocialInvitationCommand(
    val issuer: SocialInvitationIssuer,
    val agent: SocialInvitationAgent,
    val audience: SocialInvitationAudience,
    val acceptanceStatus: SocialInvitationAcceptanceStatus,
    val activePeriod: SocialInvitationActivePeriod,
    val expiryPeriod: SocialInvitationExpiryPeriod,
) : SocialInvitationCommand


@Service
public final class IssueSocialInvitationCommandHandler(
    private val factory: SocialInvitationFactory,
    private val repository: SocialInvitationRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialInvitationEvent>
) : CommandHandler<IssueSocialInvitationCommand>() {
    public override fun handle(command: IssueSocialInvitationCommand) {
        val invitation = this.factory.issueInvitation(command);
        this.eventTrigger.raise(SocialInvitationIssuedEvent.initialize(command));
        this.repository.reposit(invitation);
    }
}

