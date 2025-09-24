package org.neptrueworks.xenohermes.domain.interlocution.reaction.events

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.commands.ChangeMessageReactionCommand
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionType

public data class MessageReactionChangedEvent private constructor(
    val reactedConversation: ConversationIdentifier,
    val reactedMessage: MessageIdentifier,
    val reactor: MessageReactionReactor,
    val reactionType: MessageReactionType,
) : MessageReactionEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: ChangeMessageReactionCommand) = MessageReactionChangedEvent(
            reactedConversation = command.reactedConversation,
            reactedMessage = command.reactedMessage,
            reactor = command.reactor,
            reactionType = command.reactionType,
        )
    }
}