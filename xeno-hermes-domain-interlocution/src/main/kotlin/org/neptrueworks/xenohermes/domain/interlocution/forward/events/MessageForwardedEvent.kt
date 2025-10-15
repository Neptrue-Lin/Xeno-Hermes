package org.neptrueworks.xenohermes.domain.interlocution.forward.events

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.events.MessageCorrespondenceEvent
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForwarder
import org.neptrueworks.xenohermes.domain.interlocution.forward.commands.ForwardMessageCommand
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDeparture
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDestination

public data class MessageForwardedEvent private constructor(
    val forwarder: MessageForwarder,
    val departure: ForwardMessageDeparture,
    val destination: ForwardMessageDestination,
    val departedMessageId: MessageIdentifier,
    val destinedMessageId: MessageIdentifier,
    val departedConversation: ConversationIdentifier,
    val destinedConversation: ConversationIdentifier,
) : MessageCorrespondenceEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: ForwardMessageCommand, destinedMessageId: MessageIdentifier) = MessageForwardedEvent(
            forwarder = command.forwarder,
            departure = command.departure,
            destination = command.destination,
            departedConversation = command.departedConversationId,
            destinedConversation = command.destinedConversationId,
            departedMessageId = command.departedMessageId,
            destinedMessageId = destinedMessageId,
        )
    }
}