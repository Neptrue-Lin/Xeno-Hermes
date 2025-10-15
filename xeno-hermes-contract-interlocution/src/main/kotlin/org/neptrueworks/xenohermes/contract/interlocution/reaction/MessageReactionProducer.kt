package org.neptrueworks.xenohermes.contract.interlocution.reaction

import org.neptrueworks.xenohermes.contract.interlocution.reaction.MessageReactionDraft.`$`.produce
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.MessageReactionAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.reaction.MessageReactionFactory
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionStatus
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.springframework.stereotype.Component

@Component
internal final class MessageReactionProducer(
    override val engagementCatalogRepository: SocialEngagementCatalogingRepositable,
    override val correspondenceRepository: MessageCorrespondenceRepositable,
) : MessageReactionFactory() {
    override fun produceMessageReaction(
        conversationId: ConversationIdentifier,
        messageId: MessageIdentifier,
        reactor: MessageReactionReactor,
        status: MessageReactionStatus.Reacted,
    ): MessageReactionAggregateRoot = MessageReactionAggregator(produce {
        this.conversationId = conversationId
        this.messageId = messageId
        this.reactor = reactor
        this.reactionStatus = status
    })
}