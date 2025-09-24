package org.neptrueworks.xenohermes.domain.social.blockage.policies

import org.neptrueworks.xenohermes.domain.common.event.DomainEventHandler
import org.neptrueworks.xenohermes.domain.social.engagement.commands.EngageInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.engagement.commands.EngageInterlocutorCommandHandler
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.request.events.SocialRequestAcceptedEvent
import org.springframework.stereotype.Service

@Service
public final class InterlocutorRequestEngagementPolicy(
    private val commandHandler: EngageInterlocutorCommandHandler,
): DomainEventHandler<SocialRequestAcceptedEvent>() {
    public override fun handle(event: SocialRequestAcceptedEvent) {
        val engager = SocialEngagementEngager(event.requester.identifier);
        val engagee = SocialEngagementEngagee(event.agent.identifier);
        this.commandHandler.handle(EngageInterlocutorCommand(engager, engagee));
    }
}
