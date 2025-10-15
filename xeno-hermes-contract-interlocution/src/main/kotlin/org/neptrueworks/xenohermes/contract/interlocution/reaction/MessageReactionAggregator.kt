package org.neptrueworks.xenohermes.contract.interlocution.reaction

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.neptrueworks.xenohermes.domain.interlocution.reaction.MessageReactionAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionStatus
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionType

internal final class MessageReactionAggregator(
    base: MessageReaction?,
    draft: MessageReactingDraft = MessageReactingDraft(DraftContext(null), base),
) : MessageReactionAggregateRoot(), DraftSpi by draft, MessageReactionDraft by draft