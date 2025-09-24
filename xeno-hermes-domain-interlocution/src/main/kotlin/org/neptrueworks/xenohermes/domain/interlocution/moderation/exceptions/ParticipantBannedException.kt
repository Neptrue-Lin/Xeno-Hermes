package org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant

public final class ParticipantBannedException internal constructor(
    val participant: InterlocutionParticipant,
    val conversationId: ConversationIdentifier,
) : InterlocutionModerationException()
