package org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.events.MessageCorrespondenceEvent
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.events.MessageQuotedEvent
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageNotQuotedException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageQuotationForbiddenException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceQuoter
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageQuotationManifest
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.isNotQuoted
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme
import org.neptrueworks.xenohermes.domain.interlocution.scheme.isForbidden
import org.springframework.stereotype.Service

internal data class QuoteMessageCommand(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val quoter: MessageCorrespondenceQuoter,
    val receiver: MessageCorrespondenceReceiver,
    val quotations: MessageQuotationManifest,
    val scheme: MessageScheme,
) : MessageCorrespondenceCommand

@Service
internal final class QuoteMessageCommandHandler(
    private val correspondenceRepository: MessageCorrespondenceRepositable,
    private val eventTrigger: DomainEventRaiseable<MessageCorrespondenceEvent>
) : CommandHandler<QuoteMessageCommand>() {
    public override fun handle(command: QuoteMessageCommand) {
        if (command.quotations.isNotQuoted())
            throw MessageNotQuotedException(command.conversationId, command.messageId, command.quoter);
        if (command.scheme.quotationPermission.isForbidden())
            throw MessageQuotationForbiddenException(command.conversationId, command.messageId, command.quoter);

        val quotations = command.quotations as MessageQuotationManifest.Quoted;
        this.correspondenceRepository.fetchQuotedMessages(quotations.quotations).forEach { quotedMessage ->
            if (quotedMessage.scheme.quotationPermission.isForbidden()) {
                throw MessageQuotationForbiddenException(quotedMessage.conversationId, quotedMessage.messageId, command.quoter);
            }
        }
        this.eventTrigger.raise(MessageQuotedEvent.initialize(command));
    }
}