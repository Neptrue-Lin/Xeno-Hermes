package org.neptrueworks.xenohermes.domain.interlocution.reaction.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.MessageCorrespondenceCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.isUnsent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.MessageReactionRepositable
import org.neptrueworks.xenohermes.domain.interlocution.reaction.events.MessageReactionEvent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.events.MessageUnreactedEvent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions.ReactionAgentNotEngagedException
import org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions.ReactionMessageUnsentException
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionAgent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isNotEngaged
import org.springframework.stereotype.Service

public data class UnreactMessageCommand(
    val unreactedConversation: ConversationIdentifier,
    val unreactedMessage: MessageIdentifier,
    val unreactor: MessageReactionReactor,
    val agent: MessageReactionAgent,
) : MessageCorrespondenceCommand

@Service
public final class UnreactMessageCommandHandler(
    private val engagementCatalogRepository: SocialEngagementCatalogingRepositable,
    private val correspondenceRepository: MessageCorrespondenceRepositable,
    private val reactionRepository: MessageReactionRepositable,
    private val eventTrigger: DomainEventRaiseable<MessageReactionEvent>
) : CommandHandler<UnreactMessageCommand>() {
    public override fun handle(command: UnreactMessageCommand) {
        val unreactor = command.unreactor;
        val agent = command.agent;
        val messageId = command.unreactedMessage;
        val conversationId = command.unreactedConversation;
        
        val engagerUnreactor = SocialEngagementEngager(unreactor.identifier);
        val engageeAgent = SocialEngagementEngagee(agent.identifier);
        
        val unreactorEngagement = this.engagementCatalogRepository.fetchByIdentifier(engagerUnreactor, engageeAgent);
        if (unreactorEngagement.checkEngagement(engageeAgent).isNotEngaged())
            throw ReactionAgentNotEngagedException(unreactor, agent);
        
        val correspondence = this.correspondenceRepository.fetchByIdentifier(conversationId, messageId);
        if (correspondence.unsendStatus.isUnsent())
            throw ReactionMessageUnsentException(conversationId, messageId, unreactor);
        
        val messageReaction = this.reactionRepository.fetchByIdentifier(conversationId, messageId, unreactor);
        messageReaction.unreactMessage(command);
        this.eventTrigger.raise(MessageUnreactedEvent.initialize(command));
        this.reactionRepository.reposit(messageReaction);
    }
}
