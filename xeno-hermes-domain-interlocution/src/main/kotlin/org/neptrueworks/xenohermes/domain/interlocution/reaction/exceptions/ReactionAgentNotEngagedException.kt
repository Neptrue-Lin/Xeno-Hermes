package org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionAgent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor

public final class ReactionAgentNotEngagedException internal constructor(
    val unreactor: MessageReactionReactor,
    val agent: MessageReactionAgent
) : MessageReactionException();
