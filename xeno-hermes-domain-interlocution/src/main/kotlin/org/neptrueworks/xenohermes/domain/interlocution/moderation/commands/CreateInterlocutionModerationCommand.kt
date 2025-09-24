package org.neptrueworks.xenohermes.domain.interlocution.moderation.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationFactory
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationRepositable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.events.InterlocutionModerationCreatedEvent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.events.InterlocutionModerationEvent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBehaviorRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionContentRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.springframework.stereotype.Service

public data class CreateInterlocutionModerationCommand(
    val moderationAgent: InterlocutionModerationAgent,
    val behaviorRestriction: InterlocutionBehaviorRestriction,
    val contentRestriction: InterlocutionContentRestriction
) : InterlocutionModerationCommand

@Service
public final class CreateInterlocutionModerationCommandHandler(
    private val repository: InterlocutionModerationRepositable,
    private val factory: InterlocutionModerationFactory,
    private val eventTrigger: DomainEventRaiseable<InterlocutionModerationEvent>
) : CommandHandler<CreateInterlocutionModerationCommand>() {
    public override fun handle(command: CreateInterlocutionModerationCommand) {
        val moderation = this.factory.create(command);
        this.eventTrigger.raise(InterlocutionModerationCreatedEvent.initialize(command));
        this.repository.reposit(moderation);
    }
}