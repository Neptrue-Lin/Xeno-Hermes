package org.neptrueworks.xenohermes.domain.social.invitation

import org.neptrueworks.xenohermes.domain.common.models.DomainService
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementAggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.exceptions.InvitationEngagementAlreadyForbiddenException
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isForbidden
import org.neptrueworks.xenohermes.domain.social.invitation.commands.IssueSocialInvitationCommand
import org.neptrueworks.xenohermes.domain.social.invitation.params.*

public abstract class SocialInvitationFactory : DomainService {
    protected abstract val identifierGenerator: SocialInvitationIdentifierGenerator;
    protected abstract val engagementRepository: SocialEngagementRepositable;
    protected abstract val blockageRepository: SocialBlockageRepositable;
    
    internal final fun issueInvitation(command: IssueSocialInvitationCommand): SocialInvitationAggregateRoot {
        val engagerAgent = SocialEngagementEngager(command.agent.identifier);
        val agentEngagement: SocialEngagementAggregateRoot = this.engagementRepository.fetchByIdentifier(engagerAgent);
        if (agentEngagement.invitationEngagementPrivilege.isForbidden())
            throw InvitationEngagementAlreadyForbiddenException(engagerAgent);
        
//        val blockage = this.blockageRepository.fetchByAudiences(agent, audiences);
//        audiences.forEach { audience ->
//            if (blockage.blockageManifest.isBlocked(audience))
//                throw AudienceBlockedByAgentException(agent, audience);
//        }
        
        return produceSocialInvitation(
            invitationId = this.identifierGenerator.nextIdentifier(),
            issuer = command.issuer,
            agent = command.agent,
            audience = command.audience,
            activePeriod = command.activePeriod,
            expiryPeriod = command.expiryPeriod,
            revocationStatus = SocialInvitationRevocationStatus.ENDURING,
            acceptanceStatus = command.acceptanceStatus,
        )
    }

    protected abstract fun produceSocialInvitation(
        invitationId: SocialInvitationIdentifier,
        issuer: SocialInvitationIssuer,
        agent: SocialInvitationAgent,
        audience: SocialInvitationAudience,
        activePeriod: SocialInvitationActivePeriod,
        expiryPeriod: SocialInvitationExpiryPeriod,
        revocationStatus: SocialInvitationRevocationStatus,
        acceptanceStatus: SocialInvitationAcceptanceStatus,
    ): SocialInvitationAggregateRoot;
}
