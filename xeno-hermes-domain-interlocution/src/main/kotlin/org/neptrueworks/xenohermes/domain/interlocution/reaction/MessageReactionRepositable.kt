package org.neptrueworks.xenohermes.domain.interlocution.reaction

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor

public interface MessageReactionRepositable : AggregateRepositable<MessageReactionAggregateRoot> {
    public fun fetchByIdentifier(conversationId: ConversationIdentifier, messageId: MessageIdentifier,
                          reactor: MessageReactionReactor): MessageReactionAggregateRoot
}
