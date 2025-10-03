package org.neptrueworks.xenohermes.domain.interlocution.correspondence

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.UnsendMessageCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageAlreadyUnsentException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageUnsendForbiddenException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageUnsendNotSenderException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.UnsendWindowPastException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.*
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme
import org.neptrueworks.xenohermes.domain.interlocution.scheme.isForbidden
import org.neptrueworks.xenohermes.domain.interlocution.scheme.isUnsendWindowPast

public abstract class MessageCorrespondenceAggregateRoot : AggregateRoot(), MessageCorrespondenceAggregatable {
    public abstract override val messageId: MessageIdentifier
    public abstract override val conversationId: ConversationIdentifier
    public abstract override val sender: MessageCorrespondenceSender
    public abstract override val receiver: MessageCorrespondenceReceiver
    public abstract override val forward: MessageForward
    public abstract override val scheme: MessageScheme
    public abstract override var unsendStatus: MessageUnsendStatus protected set
    public abstract override val sendDateTime: MessageSendDateTime
    internal final fun unsendMessage(command: UnsendMessageCommand) {
        if (this.unsendStatus.isUnsent())
            throw MessageAlreadyUnsentException(command.conversationId, command.messageId, command.unsender);

        if (this.scheme.unsendPermission.isForbidden())
            throw MessageUnsendForbiddenException(command.conversationId, command.messageId, command.unsender);
        if (command.unsender.isNotSender(this.sender))
            throw MessageUnsendNotSenderException(command.conversationId, command.messageId, this.sender, command.unsender);
        if (this.scheme.unsendWindow.isUnsendWindowPast(this.sendDateTime, command.unsendDateTime))
            throw UnsendWindowPastException(command.conversationId, command.messageId, command.unsender);

        this.unsendStatus = MessageUnsendStatus.UNSENT;
    }
}
