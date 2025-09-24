package org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceUnsender

public final class MessageUnsendNotSenderException internal constructor(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val sender: MessageCorrespondenceSender,
    val unsender: MessageCorrespondenceUnsender
) : MessageCorrespondenceException()