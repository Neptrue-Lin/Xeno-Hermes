package org.neptrueworks.xenohermes.domain.social.request.policies

import org.neptrueworks.xenohermes.domain.common.event.DomainEventHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.blockage.events.InterlocutorBlockedEvent
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestResponseRepositable
import org.neptrueworks.xenohermes.domain.social.request.commands.RejectSocialRequestCommand
import org.neptrueworks.xenohermes.domain.social.request.commands.RejectSocialRequestCommandHandler
import org.neptrueworks.xenohermes.domain.social.request.events.BlockedSocialRequestRejectedEvent
import org.neptrueworks.xenohermes.domain.social.request.isNotPendingWhen
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRejectionDateTime
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestResponder
import org.springframework.stereotype.Service

@Service
public final class BlockedSocialRequestRejectionPolicy(
    private val requestResponseRepository: SocialRequestResponseRepositable,
    private val commandHandler: RejectSocialRequestCommandHandler,
    private val eventTrigger: DomainEventRaiseable<BlockedSocialRequestRejectedEvent>
) : DomainEventHandler<InterlocutorBlockedEvent>() {
    public override fun handle(event: InterlocutorBlockedEvent) {
        val requester = SocialRequestRequester(event.blocker.identifier);
        val agent = SocialRequestAgent(event.blockee.identifier);
        val socialRequest = this.requestResponseRepository.fetchPrevious(requester, agent);
        if (socialRequest.isNotPendingWhen(event.blockageDateTime.blockedAt))
            return;
        
        val responder = SocialRequestResponder(event.blocker.identifier)
        val rejectionDateTime = SocialRequestRejectionDateTime(event.blockageDateTime.blockedAt)
        this.commandHandler.handle(RejectSocialRequestCommand(socialRequest.requestId, responder, rejectionDateTime));
        this.eventTrigger.raise(BlockedSocialRequestRejectedEvent.initialize(event));
    }
}
