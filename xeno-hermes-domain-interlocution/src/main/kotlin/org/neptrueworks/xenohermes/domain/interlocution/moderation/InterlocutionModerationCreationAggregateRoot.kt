package org.neptrueworks.xenohermes.domain.interlocution.moderation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.moderation.commands.BanParticipantCommand
import org.neptrueworks.xenohermes.domain.interlocution.moderation.commands.UnbanParticipantCommand
import org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions.InterlocutionBanForbiddenException
import org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions.InterlocutionBanNotBannedException
import org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions.ParticipantAlreadyBannedException
import org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions.ParticipantNotBannedException
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.*

public abstract class InterlocutionModerationCreationAggregateRoot : AggregateRoot(), InterlocutionModerationAggregatable {
    public abstract override val moderationAgent: InterlocutionModerationAgent
    public abstract override val interlocutionBans: InterlocutionBanManifest
    public abstract override val contentRestriction: InterlocutionContentRestriction
    public abstract override val behaviorRestriction: InterlocutionBehaviorRestriction
}