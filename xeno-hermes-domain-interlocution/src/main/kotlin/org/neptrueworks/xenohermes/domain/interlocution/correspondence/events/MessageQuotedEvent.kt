package org.neptrueworks.xenohermes.domain.interlocution.correspondence.events

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.QuoteMessageCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceQuoter
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageQuotationManifest

public data class MessageQuotedEvent private constructor(
    val conversation: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val quoter: MessageCorrespondenceQuoter,
    val receiver: MessageCorrespondenceReceiver,
    val quotations: MessageQuotationManifest.Quoted,
) : MessageCorrespondenceEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: QuoteMessageCommand) = MessageQuotedEvent(
            conversation = command.conversationId,
            messageId = command.messageId,
            quoter = command.quoter,
            receiver = command.receiver,
            quotations = command.quotations as MessageQuotationManifest.Quoted,
        )
    }
}