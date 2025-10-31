package org.neptrueworks.xenohermes.domain.social.invitation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRootFactory
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.exceptions.InvitationEngagementAlreadyForbiddenException
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isForbidden
import org.neptrueworks.xenohermes.domain.social.invitation.commands.IssueSocialInvitationCommand
import org.neptrueworks.xenohermes.domain.social.invitation.params.*

public abstract class SocialInvitationIssuingFactory : AggregateRootFactory() {
    protected abstract val identifierGenerator: SocialInvitationIdentifierGenerator;
    protected abstract val engagementCatalogRepository: SocialEngagementCatalogingRepositable;
    protected abstract val blockageCatalogRepository: SocialBlockageCatalogingRepositable;
    
    internal final fun issueInvitation(command: IssueSocialInvitationCommand): SocialInvitationIssuingAggregateRoot {
        val engagerAgent = SocialEngagementEngager(command.agent.identifier);
        val engageeAudience = SocialEngagementEngagee(command.audience.identifier);
        
        val agentEngagement = this.engagementCatalogRepository.fetchByIdentifier(engagerAgent, engageeAudience);
        if (agentEngagement.invitationEngagementPrivilege.isForbidden())
            throw InvitationEngagementAlreadyForbiddenException(engagerAgent);
        
//        val blockage = this.blockageCatalogRepository.fetchByAudiences(agent, audiences);
//        audiences.forEach { audience ->
//            if (blockage.blockageList.isBlocked(audience))
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
            acceptanceStatus = SocialInvitationAcceptanceStatus.ACCEPTED,
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
    ): SocialInvitationIssuingAggregateRoot;
}
