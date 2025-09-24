package org.neptrueworks.xenohermes.domain.interlocution.moderation.events

import org.neptrueworks.xenohermes.domain.interlocution.moderation.commands.BanParticipantCommand
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBan
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerator
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant

public data class ParticipantBannedEvent private constructor(
    val moderationAgent: InterlocutionModerationAgent,
    val moderator: InterlocutionModerator,
    val participant: InterlocutionParticipant,
    val ban: InterlocutionBan,
) : InterlocutionModerationEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: BanParticipantCommand) = ParticipantBannedEvent(
            moderationAgent = command.moderationAgent,
            moderator = command.moderator,
            participant = command.participant,
            ban = command.ban,
        )
    }
}
