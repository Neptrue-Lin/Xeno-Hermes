package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialRequestEngagementForbiddenEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

public data class ForbidRequestEngagementCommand(
    val engager: SocialEngagementEngager,
) : SocialEngagementCommand

@Service
public final class ForbidRequestEngagementCommandHandler (
    private val repository: SocialEngagementRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
): CommandHandler<ForbidRequestEngagementCommand>() {
    public override fun handle(command: ForbidRequestEngagementCommand) {
        val socialEngagement = this.repository.fetchByIdentifier(command.engager);
        socialEngagement.forbidRequestEngagement(command);
        this.eventTrigger.raise(SocialRequestEngagementForbiddenEvent.initialize(command));
        this.repository.reposit(socialEngagement);
    }
}