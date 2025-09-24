package org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerator

public final class InterlocutionBanForbiddenException internal constructor(
    val moderationAgent: InterlocutionModerationAgent,
    val moderator: InterlocutionModerator
) : InterlocutionModerationException()