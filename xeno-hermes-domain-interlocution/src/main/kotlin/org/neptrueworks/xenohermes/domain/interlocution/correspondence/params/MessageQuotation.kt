package org.neptrueworks.xenohermes.domain.interlocution.correspondence.params

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier

public data class MessageQuotation(
    val quotedConversation: ConversationIdentifier,
    val quotedMessage: MessageIdentifier
)