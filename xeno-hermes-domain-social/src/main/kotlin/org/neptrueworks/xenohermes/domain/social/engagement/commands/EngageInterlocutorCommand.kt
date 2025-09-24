package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.InterlocutorEngagedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

public data class EngageInterlocutorCommand(
    val engager: SocialEngagementEngager,
    val engagee: SocialEngagementEngagee,
) : SocialEngagementCommand

@Service
public final class EngageInterlocutorCommandHandler(
    private val repository: SocialEngagementRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
): CommandHandler<EngageInterlocutorCommand>() {
    public override fun handle(command: EngageInterlocutorCommand) {
        val socialEngagement = this.repository.fetchByIdentifier(command.engager);
        socialEngagement.engageInterlocutor(command);
        this.eventTrigger.raise(InterlocutorEngagedEvent.initialize(command));
        this.repository.reposit(socialEngagement);
    }
}
