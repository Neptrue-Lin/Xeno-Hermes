package org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender

public final class MessageOverlengthException internal constructor(
    val conversationId: ConversationIdentifier,
    val sender: MessageCorrespondenceSender
) : MessageCorrespondenceException()
