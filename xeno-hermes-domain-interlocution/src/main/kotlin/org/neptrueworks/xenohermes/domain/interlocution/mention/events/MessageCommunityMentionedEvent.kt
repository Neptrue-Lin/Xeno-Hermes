package org.neptrueworks.xenohermes.domain.interlocution.mention.events

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.mention.MessageCommunityMention
import org.neptrueworks.xenohermes.domain.interlocution.mention.commands.MentionCommunityCommand
import org.neptrueworks.xenohermes.domain.interlocution.mention.exceptions.MessageNotMentionedCommunityException
import org.neptrueworks.xenohermes.domain.interlocution.mention.params.MessageMentionMentioner

public data class MessageCommunityMentionedEvent private constructor(
    val conversation: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val mentioner: MessageMentionMentioner,
    val mentioned: MessageCommunityMention.Mentioned,
): MessageMentionedEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: MentionCommunityCommand) = when (command.mentioned) {
            is MessageCommunityMention.NotMentioned -> throw MessageNotMentionedCommunityException(
                conversationId = command.conversationId,
                messageId = command.messageId,
                mentioner = command.mentioner,
            )
            is MessageCommunityMention.Mentioned -> MessageCommunityMentionedEvent(
                conversation = command.conversationId,
                messageId = command.messageId,
                mentioner = command.mentioner,
                mentioned = command.mentioned,
            )
        }
    }
}