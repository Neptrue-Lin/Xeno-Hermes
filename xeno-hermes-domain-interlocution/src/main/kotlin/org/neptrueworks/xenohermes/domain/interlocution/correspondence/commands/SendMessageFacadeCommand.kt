package org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageSendingFactory
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageSendingRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.events.MessageCorrespondenceEvent
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.events.MessageSentEvent
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageContent
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageSendDateTime
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageLength
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme
import org.springframework.stereotype.Service

public data class SendMessageFacadeCommand(
    val conversationId: ConversationIdentifier,
    val sender: MessageCorrespondenceSender,
    val receiver: MessageCorrespondenceReceiver,
    val content: MessageContent,
    val length: MessageLength,
    val scheme: MessageScheme,
//    val quotations: MessageQuotationManifest,
//    val mentions: MessageMentionManifest,
    val sendDateTime: MessageSendDateTime
) : MessageCorrespondenceCommand

@Service
public final class SendMessageFacadeCommandHandler internal constructor(
    private val factory: MessageSendingFactory,
    private val repository: MessageSendingRepositable,
    private val eventTrigger: DomainEventRaiseable<MessageCorrespondenceEvent>
) : CommandHandler<SendMessageFacadeCommand>() {
    public override fun handle(command: SendMessageFacadeCommand) {
        val correspondence = this.factory.sendMessage(command);
//        if (command.quotations.isQuoted())  
//            this.quoteCommandHandler.handle(command);
//        if (command.mentions.isMentioned()) 
//            this.mentionCommandHandler.handle(command);
        this.eventTrigger.raise(MessageSentEvent.initialize(command, correspondence.messageId))
        this.repository.reposit(correspondence);
    }
}