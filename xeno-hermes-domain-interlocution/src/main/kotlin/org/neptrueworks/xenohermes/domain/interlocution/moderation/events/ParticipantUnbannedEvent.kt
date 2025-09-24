package org.neptrueworks.xenohermes.domain.interlocution.moderation.events

import org.neptrueworks.xenohermes.domain.interlocution.moderation.commands.UnbanParticipantCommand
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerator
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant

public data class ParticipantUnbannedEvent private constructor(
    val moderationAgent: InterlocutionModerationAgent,
    val moderator: InterlocutionModerator,
    val participant: InterlocutionParticipant,
) : InterlocutionModerationEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: UnbanParticipantCommand) = ParticipantUnbannedEvent(
            moderationAgent = command.moderationAgent,
            moderator = command.moderator,
            participant = command.participant,
        )
    }
}

