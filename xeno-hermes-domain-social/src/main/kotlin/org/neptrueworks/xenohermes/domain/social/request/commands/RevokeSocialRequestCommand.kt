package org.neptrueworks.xenohermes.domain.social.request.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEvent
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestRepositable
import org.neptrueworks.xenohermes.domain.social.request.events.SocialRequestRevokedEvent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevocationDateTime
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevoker
import org.springframework.stereotype.Service

public data class RevokeSocialRequestCommand(
    val requestId: SocialRequestIdentifier,
    val revoker: SocialRequestRevoker,
    val revocationDateTime: SocialRequestRevocationDateTime
) : SocialRequestCommand

@Service
public final class RevokeSocialRequestCommandHandler(
    private val socialRequestRepository: SocialRequestRepositable,
    private val eventTrigger: DomainEventRaiseable<DomainEvent>
) : CommandHandler<RevokeSocialRequestCommand>() {
    public override fun handle(command: RevokeSocialRequestCommand) {
        val socialRequest = this.socialRequestRepository.fetchByIdentifier(command.requestId);
        socialRequest.revokeSocialRequest(command);
        this.eventTrigger.raise(SocialRequestRevokedEvent.initialize(command, socialRequest.requester, socialRequest.agent));
        this.socialRequestRepository.reposit(socialRequest);
    }
}
