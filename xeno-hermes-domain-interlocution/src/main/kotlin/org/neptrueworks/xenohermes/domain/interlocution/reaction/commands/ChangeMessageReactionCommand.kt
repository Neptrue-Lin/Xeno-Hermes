package org.neptrueworks.xenohermes.domain.interlocution.reaction.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.MessageCorrespondenceCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.isUnsent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.MessageReactionRepositable
import org.neptrueworks.xenohermes.domain.interlocution.reaction.events.MessageReactionChangedEvent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions.ReactionAgentNotEngagedException
import org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions.ReactionMessageUnsentException
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionAgent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionType
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isNotEngaged

public data class ChangeMessageReactionCommand(
    val reactor: MessageReactionReactor,
    val agent: MessageReactionAgent,
    val reactionType: MessageReactionType,
    val reactedConversation: ConversationIdentifier,
    val reactedMessage: MessageIdentifier,
) : MessageCorrespondenceCommand;

public final class ChangeMessageReactionCommandHandler(
    private val engagementRepositable: SocialEngagementRepositable,
    private val correspondenceRepository: MessageCorrespondenceRepositable,
    private val reactionRepository: MessageReactionRepositable,
    private val eventTrigger: DomainEventRaiseable<MessageReactionChangedEvent>
) : CommandHandler<ChangeMessageReactionCommand>() {
    public override fun handle(command: ChangeMessageReactionCommand) {
        val reactor = command.reactor;
        val agent = command.agent;
        val messageId = command.reactedMessage;
        val conversationId = command.reactedConversation;

        val engagerReactor = SocialEngagementEngager(reactor.identifier);
        val engageeAgent = SocialEngagementEngagee(agent.identifier);

        val reactorEngagement = this.engagementRepositable.fetchByIdentifier(engagerReactor);
        if (reactorEngagement.checkEngagement(engageeAgent).isNotEngaged())
            throw ReactionAgentNotEngagedException(reactor, agent);
        
        val correspondence = this.correspondenceRepository.fetchByIdentifier(conversationId, messageId);
        if (correspondence.unsendStatus.isUnsent())
            throw ReactionMessageUnsentException(conversationId, messageId, reactor);
        
        val messageReaction = this.reactionRepository.fetchByIdentifier(conversationId, messageId, reactor);
        messageReaction.changeReaction(command);
        this.reactionRepository.reposit(messageReaction);
        this.eventTrigger.raise(MessageReactionChangedEvent.initialize(command));
    }
}
