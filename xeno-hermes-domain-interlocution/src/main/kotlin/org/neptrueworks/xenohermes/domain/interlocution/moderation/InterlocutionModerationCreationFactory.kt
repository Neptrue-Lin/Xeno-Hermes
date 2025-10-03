package org.neptrueworks.xenohermes.domain.interlocution.moderation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRootFactory
import org.neptrueworks.xenohermes.domain.interlocution.moderation.commands.CreateInterlocutionModerationCommand
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBehaviorRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionContentRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent

public abstract class InterlocutionModerationCreationFactory : AggregateRootFactory() {
    internal final fun create(command: CreateInterlocutionModerationCommand): InterlocutionModerationCreationAggregateRoot {
        return produceInterlocutionModeration(
            moderationAgent = command.moderationAgent,
            behaviorRestriction = command.behaviorRestriction,
            contentRestriction = command.contentRestriction,
        )
    }
    
    protected abstract fun produceInterlocutionModeration(
        moderationAgent: InterlocutionModerationAgent,
        behaviorRestriction: InterlocutionBehaviorRestriction,
        contentRestriction: InterlocutionContentRestriction,
    ): InterlocutionModerationCreationAggregateRoot
}