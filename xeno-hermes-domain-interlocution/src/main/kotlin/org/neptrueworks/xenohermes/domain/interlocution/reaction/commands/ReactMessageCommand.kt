package org.neptrueworks.xenohermes.domain.interlocution.reaction.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.MessageCorrespondenceCommand
import org.neptrueworks.xenohermes.domain.interlocution.reaction.MessageReactionFactory
import org.neptrueworks.xenohermes.domain.interlocution.reaction.MessageReactionRepositable
import org.neptrueworks.xenohermes.domain.interlocution.reaction.events.MessageReactedEvent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.events.MessageReactionEvent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionAgent
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionType
import org.springframework.stereotype.Service

public data class ReactMessageCommand(
    val reactor: MessageReactionReactor,
    val agent: MessageReactionAgent,
    val reactionType: MessageReactionType,
    val reactedConversation: ConversationIdentifier,
    val reactedMessage: MessageIdentifier,
) : MessageCorrespondenceCommand

@Service
public final class ReactMessageCommandHandler(
    private val messageReactionRepository: MessageReactionRepositable,
    private val factory: MessageReactionFactory,
    private val eventTrigger: DomainEventRaiseable<MessageReactionEvent>,
) : CommandHandler<ReactMessageCommand>() {
    public override fun handle(command: ReactMessageCommand) {        
        val messageReaction = this.factory.reactMessage(command);
        this.eventTrigger.raise(MessageReactedEvent.initialize(command));
        this.messageReactionRepository.reposit(messageReaction);
    }
}
