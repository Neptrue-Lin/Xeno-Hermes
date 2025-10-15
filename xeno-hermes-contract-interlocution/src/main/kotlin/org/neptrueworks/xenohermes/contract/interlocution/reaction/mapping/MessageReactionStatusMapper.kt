package org.neptrueworks.xenohermes.contract.interlocution.reaction.mapping

import org.babyfish.jimmer.sql.runtime.ScalarProvider
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionStatus
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionType
import org.springframework.stereotype.Component

@Component
public final class MessageReactionStatusMapper : ScalarProvider<Long, MessageReactionStatus> {
    public override fun toScalar(sqlValue: MessageReactionStatus) = when(sqlValue) {
        is MessageReactionStatus.NotReacted -> 0L
        is MessageReactionStatus.Reacted    -> sqlValue.type.typeId
    }
    
    public override fun toSql(scalarValue: Long) = when(scalarValue) {
        0L   -> MessageReactionStatus.NotReacted
        else -> MessageReactionStatus.Reacted(MessageReactionType(scalarValue))
    }
}