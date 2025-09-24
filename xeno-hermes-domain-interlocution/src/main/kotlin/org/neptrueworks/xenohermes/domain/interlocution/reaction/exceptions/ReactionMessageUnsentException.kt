package org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor

public final class ReactionMessageUnsentException internal constructor(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val unreactor: MessageReactionReactor
) : MessageReactionException();
