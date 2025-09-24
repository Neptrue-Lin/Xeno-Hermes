package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialRequestEngagementPermittedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

public data class PermitRequestEngagementCommand(
    val engager: SocialEngagementEngager,
) : SocialEngagementCommand

@Service
public final class PermitRequestEngagementCommandHandler(
    private val repository: SocialEngagementRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
) : CommandHandler<PermitRequestEngagementCommand>() {
    public override fun handle(command: PermitRequestEngagementCommand) {
        val socialEngagement = repository.fetchByIdentifier(command.engager);
        socialEngagement.permitRequestEngagement(command);
        this.eventTrigger.raise(SocialRequestEngagementPermittedEvent.initialize(command));
        this.repository.reposit(socialEngagement);
    }
}