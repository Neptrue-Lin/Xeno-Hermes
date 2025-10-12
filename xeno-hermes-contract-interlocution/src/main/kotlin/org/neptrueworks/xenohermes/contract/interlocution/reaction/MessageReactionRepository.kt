package org.neptrueworks.xenohermes.contract.interlocution.reaction

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.MessageReactionAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.reaction.MessageReactionRepositable
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.springframework.stereotype.Repository

@Repository
internal final class MessageReactionRepository(
    private val jimmerClient: KSqlClient
) : MessageReactionRepositable {
    override fun fetchByIdentifier(conversationId: ConversationIdentifier, messageId: MessageIdentifier, 
                                   reactor: MessageReactionReactor): MessageReactionAggregateRoot {
        return this.jimmerClient.createQuery(MessageReaction::class) {
            where(table.conversationId eq conversationId)
            where(table.messageId eq messageId)
            where(table.reactor eq reactor)
            select(table)
        }.fetchOne().run(::MessageReactionAggregator)
    }

    override fun reposit(aggregateRoot: MessageReactionAggregateRoot) {
        val aggregator = aggregateRoot as MessageReactionAggregator;
        this.jimmerClient.save(aggregator.__resolve() as MessageReaction);
    }
}