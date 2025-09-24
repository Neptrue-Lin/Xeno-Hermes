package org.neptrueworks.xenohermes.domain.interlocution.reaction.events

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.commands.ReactMessageCommand
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionAgent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionType

public data class MessageReactedEvent private constructor(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val reactor: MessageReactionReactor,
    val agent: MessageReactionAgent,
    val reactionType: MessageReactionType,
) : MessageReactionEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: ReactMessageCommand) = MessageReactedEvent(
            conversationId = command.reactedConversation,
            messageId = command.reactedMessage,
            reactor = command.reactor,
            agent = command.agent,
            reactionType = command.reactionType,
        )
    }
}
