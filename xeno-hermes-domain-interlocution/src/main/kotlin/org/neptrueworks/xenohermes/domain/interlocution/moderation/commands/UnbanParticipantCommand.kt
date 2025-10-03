package org.neptrueworks.xenohermes.domain.interlocution.moderation.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationRepositable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.events.InterlocutionModerationEvent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.events.ParticipantUnbannedEvent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerator
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant
import org.springframework.stereotype.Service

public data class UnbanParticipantCommand(
    val moderationAgent: InterlocutionModerationAgent,
    val moderator: InterlocutionModerator,
    val participant: InterlocutionParticipant,
) : InterlocutionModerationCommand

@Service
public final class UnbanParticipantCommandHandler(
    private val repository: InterlocutionModerationRepositable,
    private val eventTrigger: DomainEventRaiseable<InterlocutionModerationEvent>
) : CommandHandler<UnbanParticipantCommand>() {
    public override fun handle(command: UnbanParticipantCommand) {
        val moderation = this.repository.fetchByIdentifier(command.moderationAgent, command.participant);
        moderation.unbanParticipant(command);
        this.eventTrigger.raise(ParticipantUnbannedEvent.initialize(command));
        this.repository.reposit(moderation);
    }
}