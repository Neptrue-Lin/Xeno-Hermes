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
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageQuotation
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageUnsendStatus
import org.springframework.stereotype.Repository

@Repository
internal final class MessageCorrespondenceRepository(
    private val kSqlClient: KSqlClient
) : MessageCorrespondenceRepositable {
    override fun fetchByIdentifier(conversationId: ConversationIdentifier, messageId: MessageIdentifier): MessageCorrespondenceAggregateRoot {
        return this.kSqlClient.createQuery(MessageCorrespondence::class) {
            where(table.conversationId eq conversationId)
            where(table.messageId eq messageId)
            select(table)
        }.fetchOne().run(::MessageCorrespondenceAggregator)
    }

    override fun fetchQuotedMessages(quotations: Iterable<MessageQuotation>): Iterable<MessageCorrespondenceAggregatable> {
        return this.kSqlClient.createQuery(MessageCorrespondence::class) {
            where(tuple(table.conversationId, table.messageId) valueIn quotations.quotedMessages())
            where(table.unsendStatus eq MessageUnsendStatus.NOT_UNSENT)
            select(table)
        }.execute();
    }

    override fun reposit(aggregateRoot: MessageCorrespondenceAggregateRoot) {
        val aggregator = aggregateRoot as MessageCorrespondenceAggregator;
        this.kSqlClient.save(aggregator.__resolve() as MessageCorrespondence);
    }
    
    private inline fun Iterable<MessageQuotation>.quotedMessages() = this.map { 
        Tuple2(it.quotedConversation, it.quotedMessage)
    }
}