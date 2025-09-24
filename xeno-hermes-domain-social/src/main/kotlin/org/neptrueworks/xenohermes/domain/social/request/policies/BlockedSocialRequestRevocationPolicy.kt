package org.neptrueworks.xenohermes.domain.social.request.policies

import org.neptrueworks.xenohermes.domain.common.event.DomainEventHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.blockage.events.InterlocutorBlockedEvent
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestRepositable
import org.neptrueworks.xenohermes.domain.social.request.commands.RevokeSocialRequestCommand
import org.neptrueworks.xenohermes.domain.social.request.commands.RevokeSocialRequestCommandHandler
import org.neptrueworks.xenohermes.domain.social.request.events.BlockedSocialRequestRevokedEvent
import org.neptrueworks.xenohermes.domain.social.request.isNotPendingWhen
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevocationDateTime
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevoker
import org.springframework.stereotype.Service

@Service
public final class BlockedSocialRequestRevocationPolicy(
    private val socialRequestRepository: SocialRequestRepositable,
    private val commandHandler: RevokeSocialRequestCommandHandler,
    private val eventTrigger: DomainEventRaiseable<BlockedSocialRequestRevokedEvent>
) : DomainEventHandler<InterlocutorBlockedEvent>() {
    public override fun handle(event: InterlocutorBlockedEvent) {
        val requester = SocialRequestRequester(event.blocker.identifier);
        val agent = SocialRequestAgent(event.blockee.identifier);
        val socialRequest = this.socialRequestRepository.fetchPrevious(requester, agent);
        if (socialRequest.isNotPendingWhen(event.blockageDateTime.blockedAt))
            return;
        
        val revoker = SocialRequestRevoker(event.blocker.identifier);
        val revocationDateTime = SocialRequestRevocationDateTime(event.blockageDateTime.blockedAt);
        this.commandHandler.handle(RevokeSocialRequestCommand(socialRequest.requestId, revoker, revocationDateTime));
        this.eventTrigger.raise(BlockedSocialRequestRevokedEvent.initialize(event));
    }
}
