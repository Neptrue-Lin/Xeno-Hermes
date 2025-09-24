package org.neptrueworks.xenohermes.domain.interlocution.correspondence

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageQuotation

public interface MessageCorrespondenceRepositable : AggregateRepositable<MessageCorrespondenceAggregateRoot> {
    public fun fetchByIdentifier(conversationId: ConversationIdentifier, messageId: MessageIdentifier): MessageCorrespondenceAggregateRoot
    public fun fetchQuotedMessages(quotations: Iterable<MessageQuotation>) : Iterable<MessageCorrespondenceAggregatable>
}
