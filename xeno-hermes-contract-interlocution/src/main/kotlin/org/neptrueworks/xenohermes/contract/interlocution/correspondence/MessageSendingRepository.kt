package org.neptrueworks.xenohermes.contract.interlocution.correspondence

import org.babyfish.jimmer.sql.ast.tuple.Tuple2
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.tuple
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceAggregatable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageSendingAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageSendingRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageQuotation
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageUnsendStatus
import org.springframework.stereotype.Repository

@Repository
internal final class MessageSendingRepository(
    private val jimmerClient: KSqlClient
) : MessageSendingRepositable {
    override fun reposit(aggregateRoot: MessageSendingAggregateRoot) {
        val aggregator = aggregateRoot as MessageSendingAggregator;
        this.jimmerClient.save(aggregator.resolve());
    }
}