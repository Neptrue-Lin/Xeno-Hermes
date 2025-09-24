package org.neptrueworks.xenohermes.domain.interlocution.moderation

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBanManifest
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBehaviorRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionContentRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent

public interface InterlocutionModerationAggregatable : Aggregatable {
    val moderationAgent: InterlocutionModerationAgent
//    val moderator: InterlocutionModerator
    val interlocutionBans: InterlocutionBanManifest
    val contentRestriction: InterlocutionContentRestriction
    val behaviorRestriction: InterlocutionBehaviorRestriction
//    val banPrivilege: InterlocutionBanPrivilege
}