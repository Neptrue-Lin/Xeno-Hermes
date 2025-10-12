package org.neptrueworks.xenohermes.contract.interlocution.forward

import org.babyfish.jimmer.sql.ast.mutation.AssociatedSaveMode
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.ast.tuple.Tuple2
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.tuple
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.neptrueworks.xenohermes.contract.interlocution.correspondence.MessageSendingAggregator
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceAggregatable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageSendingAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageSendingRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageQuotation
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageUnsendStatus
import org.neptrueworks.xenohermes.domain.interlocution.forward.MessageForwardingAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.forward.MessageForwardingRepositable
import org.springframework.stereotype.Repository

@Repository
internal final class MessageForwardingRepository(
    private val jimmerClient: KSqlClient,
) : MessageForwardingRepositable {
    override fun reposit(aggregateRoot: MessageForwardingAggregateRoot) {
        val aggregator = aggregateRoot as MessageForwardingAggregator;
        this.jimmerClient.saveCommand(aggregator.resolve(), SaveMode.INSERT_ONLY, AssociatedSaveMode.UPDATE).execute();
    }
}