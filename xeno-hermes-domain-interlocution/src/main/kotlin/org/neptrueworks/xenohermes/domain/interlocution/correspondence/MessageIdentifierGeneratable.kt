package org.neptrueworks.xenohermes.domain.interlocution.correspondence

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier

public interface MessageIdentifierGeneratable {
    public fun nextIdentifier(conversation: ConversationIdentifier, message: MessageIdentifier): MessageIdentifier
}
