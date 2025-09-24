package org.neptrueworks.xenohermes.domain.interlocution.mention.events

import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.mention.MessageIndividualMention
import org.neptrueworks.xenohermes.domain.interlocution.mention.commands.MentionIndividualsCommand
import org.neptrueworks.xenohermes.domain.interlocution.mention.exceptions.MessageNotMentionedIndividualException
import org.neptrueworks.xenohermes.domain.interlocution.mention.params.MessageMentionMentioner

public data class MessageIndividualMentionedEvent private constructor(
    val conversation: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val mentioner: MessageMentionMentioner,
    val mentioned: MessageIndividualMention.Mentioned,
) : MessageMentionedEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: MentionIndividualsCommand) = when (command.mentioned) {
            is MessageIndividualMention.NotMentioned -> throw MessageNotMentionedIndividualException(
                conversation = command.conversationId,
                messageId = command.messageId,
                mentioner = command.mentioner,
            )

            is MessageIndividualMention.Mentioned -> MessageIndividualMentionedEvent(
                conversation = command.conversationId,
                messageId = command.messageId,
                mentioner = command.mentioner,
                mentioned = command.mentioned,
            )
        }
    }
}