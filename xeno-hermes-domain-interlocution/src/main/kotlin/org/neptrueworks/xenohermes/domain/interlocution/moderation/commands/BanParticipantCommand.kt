package org.neptrueworks.xenohermes.domain.interlocution.moderation.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationBanningRepositable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.events.InterlocutionModerationEvent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.events.ParticipantBannedEvent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBan
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerator
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant
import org.springframework.stereotype.Component

public data class BanParticipantCommand(
    val moderationAgent: InterlocutionModerationAgent,
    val moderator: InterlocutionModerator,
    val participant: InterlocutionParticipant,
    val ban: InterlocutionBan,
) : InterlocutionModerationCommand

@Component
public final class BanParticipantCommandHandler(
    private val repository: InterlocutionModerationBanningRepositable,
    private val eventTrigger: DomainEventRaiseable<InterlocutionModerationEvent>
) : CommandHandler<BanParticipantCommand>() {
    public override fun handle(command: BanParticipantCommand) {
        val moderation = this.repository.fetchByIdentifier(command.moderationAgent);
        moderation.banParticipant(command);
        this.eventTrigger.raise(ParticipantBannedEvent.initialize(command));
        this.repository.reposit(moderation);
    }
}