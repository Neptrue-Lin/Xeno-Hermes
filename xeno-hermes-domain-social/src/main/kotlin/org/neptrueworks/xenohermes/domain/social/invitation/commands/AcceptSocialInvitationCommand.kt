package org.neptrueworks.xenohermes.domain.social.invitation.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageRepositable
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.isBlocked
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.exceptions.InvitationEngagementAlreadyForbiddenException
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isForbidden
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.events.SocialInvitationEvent
import org.neptrueworks.xenohermes.domain.social.invitation.events.SocialInvitationAcceptedEvent
import org.neptrueworks.xenohermes.domain.social.invitation.exceptions.InvitationAgentBlockedAccepter
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAcceptanceDateTime
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAccepter
import org.springframework.stereotype.Service

public data class AcceptSocialInvitationCommand(
    val invitationId: SocialInvitationIdentifier,
    val accepter: SocialInvitationAccepter,
    val acceptanceDateTime: SocialInvitationAcceptanceDateTime
) : SocialInvitationCommand

@Service
public final class AcceptSocialInvitationCommandHandler(
    private val invitationRepository: SocialInvitationRepositable,
    private val blockageRepository: SocialBlockageRepositable,
    private val engagementCatalogRepository: SocialEngagementCatalogingRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialInvitationEvent>
) : CommandHandler<AcceptSocialInvitationCommand>() {
    public override fun handle(command: AcceptSocialInvitationCommand) {
        val invitation = this.invitationRepository.fetchByIdentifier(command.invitationId);
        val agent = invitation.agent;
        val accepter = command.accepter;
        val audience = invitation.audience;
        
        val blockerAgent = SocialBlockageBlocker(agent.identifier);
        val blockeeAccepter = SocialBlockageBlockee(accepter.identifier);
        val engagerAgent = SocialEngagementEngager(agent.identifier);
        val engageeAudience = SocialEngagementEngagee(audience.identifier);
        
        
        
        val blockage = this.blockageRepository.fetchByIdentifier(blockerAgent, blockeeAccepter);
        if (blockage.blockageCatalog.checkBlockage(blockeeAccepter).isBlocked())
            throw InvitationAgentBlockedAccepter(agent, accepter);

        val engagement = this.engagementCatalogRepository.fetchByIdentifier(engagerAgent, engageeAudience);
        if (engagement.invitationEngagementPrivilege.isForbidden())
            throw InvitationEngagementAlreadyForbiddenException(engagerAgent);

        invitation.acceptSocialInvitation(command);
        this.eventTrigger.raise(SocialInvitationAcceptedEvent.initialize(command, agent));
        this.invitationRepository.reposit(invitation);
    }
}