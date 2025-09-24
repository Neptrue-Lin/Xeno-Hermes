package org.neptrueworks.xenohermes.domain.interlocution.correspondence.params

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDeparture

public sealed class MessageForward {
    public data object NotForwarded : MessageForward();
    public data class Forwarded(
        val forwardedConversationId: ConversationIdentifier,
        val forwardedMessageId: MessageIdentifier,
        val forwardDeparture: ForwardMessageDeparture
    ): MessageForward();
}