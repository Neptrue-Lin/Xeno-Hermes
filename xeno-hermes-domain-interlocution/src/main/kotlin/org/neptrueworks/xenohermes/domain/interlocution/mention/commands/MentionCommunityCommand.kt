package org.neptrueworks.xenohermes.domain.interlocution.mention.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.MessageCorrespondenceCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.events.MessageCorrespondenceEvent
import org.neptrueworks.xenohermes.domain.interlocution.mention.MessageCommunityMention
import org.neptrueworks.xenohermes.domain.interlocution.mention.events.MessageCommunityMentionedEvent
import org.neptrueworks.xenohermes.domain.interlocution.mention.exceptions.MessageNotMentionedCommunityException
import org.neptrueworks.xenohermes.domain.interlocution.mention.isNotMentioned
import org.neptrueworks.xenohermes.domain.interlocution.mention.params.MessageMentionMentioner
import org.springframework.stereotype.Service

internal data class MentionCommunityCommand(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val mentioner: MessageMentionMentioner,
    val mentioned: MessageCommunityMention,
) : MessageCorrespondenceCommand

@Service
internal final class MentionCommunityCommandHandler(
    private val eventTrigger: DomainEventRaiseable<MessageCorrespondenceEvent>
) : CommandHandler<MentionCommunityCommand>() {
    public override fun handle(command: MentionCommunityCommand) {
        if (command.mentioned.isNotMentioned())
            throw MessageNotMentionedCommunityException(command.conversationId, command.messageId, command.mentioner);
        // TODO: Only owner and moderator can mention all
        this.eventTrigger.raise(MessageCommunityMentionedEvent.initialize(command));
    }
}