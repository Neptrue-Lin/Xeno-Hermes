package org.neptrueworks.xenohermes.domain.social.request.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageAggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageRepositable
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.isBlocked
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isEngaged
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestRepositable
import org.neptrueworks.xenohermes.domain.social.request.events.SocialRequestAcceptedEvent
import org.neptrueworks.xenohermes.domain.social.request.events.SocialRequestEvent
import org.neptrueworks.xenohermes.domain.social.request.exceptions.RequestAgentAlreadyEngagedException
import org.neptrueworks.xenohermes.domain.social.request.exceptions.RequestAgentBlockedRequesterException
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAcceptanceDateTime
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestResponder
import org.springframework.stereotype.Service

public data class AcceptSocialRequestCommand(
    val requestId: SocialRequestIdentifier,
    val responder: SocialRequestResponder,
    val acceptanceDateTime: SocialRequestAcceptanceDateTime
) : SocialRequestCommand

@Service
public final class AcceptSocialRequestCommandHandler(
    private val repository: SocialRequestRepositable,
    private val blockageRepository: SocialBlockageRepositable,
    private val engagementRepository: SocialEngagementRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialRequestEvent>
) : CommandHandler<AcceptSocialRequestCommand>() {
    public override fun handle(command: AcceptSocialRequestCommand) {
        val socialRequest = this.repository.fetchByIdentifier(command.requestId);
        val requester = socialRequest.requester;
        val agent = socialRequest.agent;

        val blockerAgent = SocialBlockageBlocker(agent.identifier);
        val blockeeRequester = SocialBlockageBlockee(requester.identifier);
        val engagerAgent = SocialEngagementEngager(agent.identifier);
        val engageeRequester = SocialEngagementEngagee(requester.identifier);
    
        val agentBlockage: SocialBlockageAggregateRoot = this.blockageRepository.fetchByIdentifier(blockerAgent);
        if (agentBlockage.checkBlockage(blockeeRequester).isBlocked())
            throw RequestAgentBlockedRequesterException(agent, requester);

        val agentEngagement = this.engagementRepository.fetchByIdentifier(engagerAgent);
        if (agentEngagement.checkEngagement(engageeRequester).isEngaged())
            throw RequestAgentAlreadyEngagedException(agent, requester);

        socialRequest.acceptSocialRequest(command);
        this.eventTrigger.raise(SocialRequestAcceptedEvent.initialize(command, requester, agent));
        this.repository.reposit(socialRequest);
    }
}
