package org.neptrueworks.xenohermes.domain.interlocution.moderation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent

public interface InterlocutionModerationRepositable : AggregateRepositable<InterlocutionModerationAggregateRoot> {
    public fun fetchByIdentifier(moderationAgent: InterlocutionModerationAgent): InterlocutionModerationAggregateRoot
}