package org.neptrueworks.xenohermes.domain.interlocution.mention.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.mention.params.MessageMentionMentioner

public final class MessageNotMentionedCommunityException internal constructor(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val mentioner: MessageMentionMentioner,
) : MessageMentionException();