package org.neptrueworks.xenohermes.domain.interlocution.reaction.events

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.commands.UnreactMessageCommand
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionAgent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor

public data class MessageUnreactedEvent private constructor(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val unreactor: MessageReactionReactor,
    val agent: MessageReactionAgent,
) : MessageReactionEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: UnreactMessageCommand) = MessageUnreactedEvent(
            conversationId = command.unreactedConversation,
            messageId = command.unreactedMessage,
            unreactor = command.unreactor,
            agent = command.agent,
        )
    }
}
