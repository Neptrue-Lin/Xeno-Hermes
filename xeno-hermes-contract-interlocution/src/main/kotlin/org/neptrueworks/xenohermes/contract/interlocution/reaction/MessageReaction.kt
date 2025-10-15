package org.neptrueworks.xenohermes.contract.interlocution.reaction

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.GenerationType
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.KeyUniqueConstraint
import org.neptrueworks.xenohermes.contract.interlocution.reaction.params.MessageReactionIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.MessageReactionAggregatable
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionStatus

internal typealias MessageReactingDraft = MessageReactionDraft.`$`.DraftImpl;

@Entity
@KeyUniqueConstraint
public interface MessageReaction : MessageReactionAggregatable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val reactionId: MessageReactionIdentifier
    @Key
    override val conversationId: ConversationIdentifier
    @Key
    override val messageId: MessageIdentifier
    @Key
    override val reactor: MessageReactionReactor
    override val reactionStatus: MessageReactionStatus
}