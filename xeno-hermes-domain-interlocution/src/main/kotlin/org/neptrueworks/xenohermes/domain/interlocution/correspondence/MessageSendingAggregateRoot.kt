package org.neptrueworks.xenohermes.domain.interlocution.correspondence

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceAggregatable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.UnsendMessageCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForward
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageSendDateTime
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageUnsendStatus
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.isUnsent
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme

public abstract class MessageSendingAggregateRoot : AggregateRoot(), MessageCorrespondenceAggregatable {
    public abstract override val messageId: MessageIdentifier
    public abstract override val conversationId: ConversationIdentifier
    public abstract override val sender: MessageCorrespondenceSender
    public abstract override val receiver: MessageCorrespondenceReceiver
    public abstract override val forward: MessageForward
    public abstract override val scheme: MessageScheme
    public abstract override val unsendStatus: MessageUnsendStatus
    public abstract override val sendDateTime: MessageSendDateTime
}