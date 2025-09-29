package org.neptrueworks.xenohermes.domain.social.request

import org.neptrueworks.xenohermes.domain.common.models.DomainService
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageRepositable
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.isBlocked
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.exceptions.RequestEngagementAlreadyForbiddenException
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isEngaged
import org.neptrueworks.xenohermes.domain.social.engagement.params.isForbidden
import org.neptrueworks.xenohermes.domain.social.request.commands.SendSocialRequestCommand
import org.neptrueworks.xenohermes.domain.social.request.exceptions.RequestAgentAlreadyEngagedException
import org.neptrueworks.xenohermes.domain.social.request.exceptions.RequestAgentBlockedRequesterException
import org.neptrueworks.xenohermes.domain.social.request.params.*

public abstract class SocialRequestFactory : DomainService {
    protected abstract val blockageRepository: SocialBlockageRepositable;
    protected abstract val engagementCatalogRepository: SocialEngagementCatalogingRepositable;
    protected abstract val identifierGenerator: SocialRequestIdentifierGenerator;
    
    internal final fun sendRequest(command: SendSocialRequestCommand): SocialRequestAggregateRoot {
        val agent = command.agent;
        val requester = command.requester;
        
        val blockerAgent = SocialBlockageBlocker(agent.identifier);
        val blockeeRequester = SocialBlockageBlockee(requester.identifier);
        val engagerAgent = SocialEngagementEngager(agent.identifier);
        val engageeRequester = SocialEngagementEngagee(requester.identifier);

        val agentBlockage = this.blockageRepository.fetchByIdentifier(blockerAgent, blockeeRequester);
        if (agentBlockage.checkBlockage(blockeeRequester).isBlocked())
            throw RequestAgentBlockedRequesterException(agent, requester);
        
        val agentEngagement = this.engagementCatalogRepository.fetchByIdentifier(engagerAgent, engageeRequester);
        if (agentEngagement.checkEngagement(engageeRequester).isEngaged())
            throw RequestAgentAlreadyEngagedException(agent, requester);
        if (agentEngagement.requestEngagementPrivilege.isForbidden())
            throw RequestEngagementAlreadyForbiddenException(engagerAgent);

        return produceSocialRequest(
            requestId = this.identifierGenerator.nextIdentifier(),
            requester = requester,
            agent = agent,
            revocationStatus = SocialRequestRevocationStatus.ENDURING,
            revocationPrivilege = SocialRequestRevocationPrivilege.PERMITTED,
            responseStatus = SocialResponseStatus.NOT_RESPONDED,
            responsePrivilege = SocialResponsePrivilege.PERMITTED,
            expiryPeriod = command.expiryPeriod
        )
    }

    protected abstract fun produceSocialRequest(
        requestId: SocialRequestIdentifier,
        requester: SocialRequestRequester,
        agent: SocialRequestAgent,
        revocationStatus: SocialRequestRevocationStatus,
        revocationPrivilege: SocialRequestRevocationPrivilege,
        responseStatus: SocialResponseStatus,
        responsePrivilege: SocialResponsePrivilege,
        expiryPeriod: SocialRequestExpiryPeriod,
    ): SocialRequestAggregateRoot;
}
