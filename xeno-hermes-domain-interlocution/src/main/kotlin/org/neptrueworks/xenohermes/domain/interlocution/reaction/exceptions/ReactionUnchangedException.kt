package org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionType

public final class ReactionUnchangedException internal constructor(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val reactor: MessageReactionReactor,
    val reactionType: MessageReactionType
) : MessageReactionException()
