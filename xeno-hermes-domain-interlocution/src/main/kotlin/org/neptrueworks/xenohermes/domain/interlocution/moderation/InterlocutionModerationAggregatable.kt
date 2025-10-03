package org.neptrueworks.xenohermes.domain.interlocution.moderation

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBanPrivilege
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBehaviorRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionContentRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent

public interface InterlocutionModerationAggregatable : Aggregatable {
    val moderationAgent: InterlocutionModerationAgent
    val contentRestriction: InterlocutionContentRestriction
    val behaviorRestriction: InterlocutionBehaviorRestriction
}