package org.neptrueworks.xenohermes.domain.interlocution.reaction

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionStatus

public interface MessageReactionAggregatable : Aggregatable {
    val conversationId: ConversationIdentifier
    val messageId: MessageIdentifier
//    val reactionManifest: MessageReactionManifest
    val reactionStatus: MessageReactionStatus
    val reactor: MessageReactionReactor
}