package org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceQuoter

public final class MessageNotQuotedException internal constructor(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val quoter: MessageCorrespondenceQuoter,
) : MessageCorrespondenceException();