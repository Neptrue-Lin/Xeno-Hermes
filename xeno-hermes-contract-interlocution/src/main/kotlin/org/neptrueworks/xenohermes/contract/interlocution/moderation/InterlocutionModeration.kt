package org.neptrueworks.xenohermes.contract.interlocution.moderation

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.OneToMany
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationAggregatable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBanPrivilege
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBehaviorRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionContentRestriction
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent

internal typealias InterlocutionModeratingDraft = InterlocutionModerationDraft.`$`.DraftImpl;

@Entity
public interface InterlocutionModeration: InterlocutionModerationAggregatable {
    @Id
    override val moderationAgent: InterlocutionModerationAgent
    override val contentRestriction: InterlocutionContentRestriction
    override val behaviorRestriction: InterlocutionBehaviorRestriction
    @OneToMany(mappedBy = "moderation")
    val banned: List<InterlocutionBanning>
}