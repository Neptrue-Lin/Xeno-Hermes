package org.neptrueworks.xenohermes.domain.interlocution.reaction

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRootFactory
import org.neptrueworks.xenohermes.domain.common.models.DomainService
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.isUnsent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.commands.ReactMessageCommand
import org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions.MessageReactionForbiddenException
import org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions.ReactionAgentNotEngagedException
import org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions.ReactionMessageUnsentException
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionStatus
import org.neptrueworks.xenohermes.domain.interlocution.scheme.isForbidden
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isNotEngaged

public abstract class MessageReactionFactory(
    private val engagementRepository: SocialEngagementRepositable,
    private val correspondenceRepository: MessageCorrespondenceRepositable,
) : AggregateRootFactory(), DomainService {
    internal final fun reactMessage(command: ReactMessageCommand): MessageReactionAggregateRoot {
        val agent = command.agent;
        val reactor = command.reactor;
        val conversationId = command.reactedConversation;
        val messageId = command.reactedMessage;

        val engagerReactor = SocialEngagementEngager(reactor.identifier);
        val engageeAgent = SocialEngagementEngagee(agent.identifier);

        val agentToReactor = this.engagementRepository.fetchByIdentifier(engagerReactor);
        if (agentToReactor.checkEngagement(engageeAgent).isNotEngaged())
            throw ReactionAgentNotEngagedException(reactor, agent);

        val correspondence = this.correspondenceRepository.fetchByIdentifier(conversationId, messageId);
        if (correspondence.unsendStatus.isUnsent())
            throw ReactionMessageUnsentException(conversationId, messageId, reactor);
        if (correspondence.scheme.reactionPermission.isForbidden())
            throw MessageReactionForbiddenException(conversationId, messageId, reactor);

        return produceMessageReaction(
            conversationId = command.reactedConversation,
            messageId = command.reactedMessage,
            reactor = command.reactor,
            status = MessageReactionStatus.Reacted(command.reactionType)
        );
    }
    
    protected abstract fun produceMessageReaction(
        conversationId: ConversationIdentifier,
        messageId: MessageIdentifier,
        reactor: MessageReactionReactor,
        status: MessageReactionStatus
    ): MessageReactionAggregateRoot
}
