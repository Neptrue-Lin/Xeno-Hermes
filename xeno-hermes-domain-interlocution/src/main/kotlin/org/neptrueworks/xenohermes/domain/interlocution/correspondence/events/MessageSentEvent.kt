package org.neptrueworks.xenohermes.domain.interlocution.correspondence.events

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.SendMessageFacadeCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageContent
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender

public data class MessageSentEvent private constructor(
    val conversation: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val sender: MessageCorrespondenceSender,
    val receiver: MessageCorrespondenceReceiver,
    val content: MessageContent,
) : MessageCorrespondenceEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: SendMessageFacadeCommand, messageId: MessageIdentifier) = MessageSentEvent(
            conversation = command.conversationId,
            messageId = messageId,
            sender = command.sender,
            receiver = command.receiver,
            content = command.content,
        )
    }
}
