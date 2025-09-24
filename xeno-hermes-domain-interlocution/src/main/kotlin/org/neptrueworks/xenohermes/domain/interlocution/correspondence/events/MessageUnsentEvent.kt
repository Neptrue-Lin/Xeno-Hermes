package org.neptrueworks.xenohermes.domain.interlocution.correspondence.events

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.UnsendMessageCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceUnsender

public data class MessageUnsentEvent private constructor(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val unsender: MessageCorrespondenceUnsender,
    val receiver: MessageCorrespondenceReceiver,
) : MessageCorrespondenceEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: UnsendMessageCommand) = MessageUnsentEvent(
            conversationId = command.conversationId,
            messageId = command.messageId,
            unsender = command.unsender,
            receiver = command.receiver,
        )
    }
}
