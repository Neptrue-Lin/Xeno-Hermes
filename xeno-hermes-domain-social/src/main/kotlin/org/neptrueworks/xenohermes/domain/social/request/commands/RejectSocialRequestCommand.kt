package org.neptrueworks.xenohermes.domain.social.request.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestRepositable
import org.neptrueworks.xenohermes.domain.social.request.events.SocialRequestEvent
import org.neptrueworks.xenohermes.domain.social.request.events.SocialRequestRejectedEvent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRejectionDateTime
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestResponder
import org.springframework.stereotype.Service

public data class RejectSocialRequestCommand(
    val requestId: SocialRequestIdentifier,
    val responder: SocialRequestResponder,
    val rejectionDateTime: SocialRequestRejectionDateTime
) : SocialRequestCommand

@Service
public final class RejectSocialRequestCommandHandler(
    private val repository: SocialRequestRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialRequestEvent>
) : CommandHandler<RejectSocialRequestCommand>() {
    public override fun handle(command: RejectSocialRequestCommand) {
        val socialRequest = this.repository.fetchByIdentifier(command.requestId);
        socialRequest.rejectSocialRequest(command);
        this.eventTrigger.raise(SocialRequestRejectedEvent.initialize(command, socialRequest.requester, socialRequest.agent));
        this.repository.reposit(socialRequest);
    }
}

