package org.neptrueworks.xenohermes.domain.interlocution.correspondence

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.*
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme

public interface MessageCorrespondenceAggregatable : Aggregatable {
    val conversationId: ConversationIdentifier
    val messageId: MessageIdentifier
    val sender: MessageCorrespondenceSender
    val receiver: MessageCorrespondenceReceiver
    val scheme: MessageScheme
    val unsendStatus: MessageUnsendStatus
    val forward: MessageForward
    val sendDateTime: MessageSendDateTime
}