package org.neptrueworks.xenohermes.domain.interlocution.forward.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.MessageCorrespondenceCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.events.MessageCorrespondenceEvent
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForwarder
import org.neptrueworks.xenohermes.domain.interlocution.forward.MessageForwardingFactory
import org.neptrueworks.xenohermes.domain.interlocution.forward.MessageForwardingRepositable
import org.neptrueworks.xenohermes.domain.interlocution.forward.events.MessageForwardedEvent
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDeparture
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDestination
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.MessageForwardDateTime
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme
import org.springframework.stereotype.Service

public data class ForwardMessageCommand(
    val forwarder: MessageForwarder,
    val departure: ForwardMessageDeparture,
    val destination: ForwardMessageDestination,
    val departedConversationId: ConversationIdentifier,
    val departedMessageId: MessageIdentifier,
    val destinedConversationId: ConversationIdentifier,
    val forwardDateTime: MessageForwardDateTime,
    val scheme: MessageScheme
) : MessageCorrespondenceCommand

@Service
public final class ForwardMessageCommandHandler(
    private val factory: MessageForwardingFactory,
    private val repository: MessageForwardingRepositable,
    private val eventTrigger: DomainEventRaiseable<MessageCorrespondenceEvent>
) : CommandHandler<ForwardMessageCommand>() {
    public override fun handle(command: ForwardMessageCommand) {
        val correspondence = this.factory.forwardMessage(command);
        this.eventTrigger.raise(MessageForwardedEvent.initialize(command, correspondence.messageId));
        this.repository.reposit(correspondence);
    }
}
