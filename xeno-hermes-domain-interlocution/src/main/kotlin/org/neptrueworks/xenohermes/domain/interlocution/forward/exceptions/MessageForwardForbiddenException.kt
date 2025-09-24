package org.neptrueworks.xenohermes.domain.interlocution.forward.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageCorrespondenceException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForwarder

public final class MessageForwardForbiddenException internal constructor(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val forwarder: MessageForwarder
) : MessageCorrespondenceException()
