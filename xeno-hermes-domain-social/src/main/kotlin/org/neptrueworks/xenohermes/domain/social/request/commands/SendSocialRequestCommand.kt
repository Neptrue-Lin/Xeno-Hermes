package org.neptrueworks.xenohermes.domain.social.request.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestSendingFactory
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestResponseRepositable
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestSendingRepositable
import org.neptrueworks.xenohermes.domain.social.request.events.SocialRequestEvent
import org.neptrueworks.xenohermes.domain.social.request.events.SocialRequestSentEvent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestExpiryPeriod
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestSendDateTime
import org.springframework.stereotype.Service

public data class SendSocialRequestCommand(
    val requester: SocialRequestRequester,
    val agent: SocialRequestAgent,
    val requestDateTime: SocialRequestSendDateTime,
    val expiryPeriod: SocialRequestExpiryPeriod
) : SocialRequestCommand


@Service
public final class SendSocialRequestCommandHandler(
    private val repository: SocialRequestSendingRepositable,
    private val factory: SocialRequestSendingFactory,
    private val eventTrigger: DomainEventRaiseable<SocialRequestEvent>
) : CommandHandler<SendSocialRequestCommand>() {
    public override fun handle(command: SendSocialRequestCommand) {
        val socialRequest = this.factory.sendRequest(command);
        this.eventTrigger.raise(SocialRequestSentEvent.initialize(command, socialRequest.requestId));
        this.repository.reposit(socialRequest);
    }
}
