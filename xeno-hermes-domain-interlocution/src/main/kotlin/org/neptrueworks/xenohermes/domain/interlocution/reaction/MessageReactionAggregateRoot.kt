package org.neptrueworks.xenohermes.domain.interlocution.reaction

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.reaction.commands.ChangeMessageReactionCommand
import org.neptrueworks.xenohermes.domain.interlocution.reaction.commands.UnreactMessageCommand
import org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions.MessageNotReactedException
import org.neptrueworks.xenohermes.domain.interlocution.reaction.exceptions.ReactionUnchangedException
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionReactor
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionStatus
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.MessageReactionStatus.Reacted
import org.neptrueworks.xenohermes.domain.interlocution.reaction.params.isNotReacted

public abstract class MessageReactionAggregateRoot : AggregateRoot(), MessageReactionAggregatable {
    public abstract override val conversationId: ConversationIdentifier
    public abstract override val messageId: MessageIdentifier
    public abstract override val reactor: MessageReactionReactor
    public abstract override var reactionStatus: MessageReactionStatus protected set

    internal final fun changeReaction(command: ChangeMessageReactionCommand) = when (this.reactionStatus) {
        is MessageReactionStatus.NotReacted -> throw MessageNotReactedException(command.reactedConversation, command.reactedMessage, command.reactor);
        is MessageReactionStatus.Reacted -> {
            if ((this.reactionStatus as Reacted).type.remainsUnchanged(command.reactionType))
                throw ReactionUnchangedException(command.reactedConversation, command.reactedMessage, command.reactor, command.reactionType);
            this.reactionStatus = Reacted(command.reactionType);
        }
    }
    
    internal final fun unreactMessage(command: UnreactMessageCommand) {
        if (this.reactionStatus.isNotReacted())
            throw MessageNotReactedException(command.unreactedConversation, command.unreactedMessage, command.unreactor);
        
        this.reactionStatus = MessageReactionStatus.NotReacted;
    }
}
