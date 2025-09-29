package org.neptrueworks.xenohermes.domain.social.request.policies

import org.neptrueworks.xenohermes.domain.common.event.DomainEventHandler
import org.neptrueworks.xenohermes.domain.social.engagement.commands.EstablishEngagementCommand
import org.neptrueworks.xenohermes.domain.social.engagement.commands.EstablishEngagementCommandHandler
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementMethod
import org.neptrueworks.xenohermes.domain.social.request.events.SocialRequestAcceptedEvent
import org.springframework.stereotype.Service

@Service
public final class RequestEngagementEstablishmentPolicy(
    private val commandHandler: EstablishEngagementCommandHandler,
): DomainEventHandler<SocialRequestAcceptedEvent>() {
    public override fun handle(event: SocialRequestAcceptedEvent) {
        val engager = SocialEngagementEngager(event.requester.identifier);
        val engagee = SocialEngagementEngagee(event.agent.identifier);
        val method = SocialEngagementMethod.Request(event.requestId);
        this.commandHandler.handle(EstablishEngagementCommand(engager, engagee, method));
    }
}