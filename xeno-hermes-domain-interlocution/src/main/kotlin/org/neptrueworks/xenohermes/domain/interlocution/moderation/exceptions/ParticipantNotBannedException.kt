package org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerator
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant

public final class ParticipantNotBannedException internal constructor(
    val moderationAgent: InterlocutionModerationAgent,
    val moderator: InterlocutionModerator,
    val participant: InterlocutionParticipant
) : InterlocutionModerationException();