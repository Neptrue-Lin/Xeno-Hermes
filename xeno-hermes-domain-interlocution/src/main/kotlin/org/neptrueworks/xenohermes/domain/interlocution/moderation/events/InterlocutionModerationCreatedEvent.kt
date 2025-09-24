package org.neptrueworks.xenohermes.domain.interlocution.moderation.events

import org.neptrueworks.xenohermes.domain.interlocution.moderation.commands.CreateInterlocutionModerationCommand
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBehaviorRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionContentRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent

public data class InterlocutionModerationCreatedEvent(
    val moderationAgent: InterlocutionModerationAgent,
    val behaviorRestriction: InterlocutionBehaviorRestriction,
    val contentRestriction: InterlocutionContentRestriction
) : InterlocutionModerationEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: CreateInterlocutionModerationCommand) = InterlocutionModerationCreatedEvent(
            moderationAgent = command.moderationAgent,
            behaviorRestriction = command.behaviorRestriction,
            contentRestriction = command.contentRestriction,
        )
    }
}