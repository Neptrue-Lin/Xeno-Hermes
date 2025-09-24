package org.neptrueworks.xenohermes.domain.interlocution.reaction.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class MessageReactionType(val typeId: Long) {
    public inline fun remainsUnchanged(reactionType: MessageReactionType) = 
        this.typeId == reactionType.typeId;
}
