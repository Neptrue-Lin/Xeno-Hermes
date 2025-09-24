package org.neptrueworks.xenohermes.domain.interlocution.mention.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.MessageCorrespondenceCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.events.MessageCorrespondenceEvent
import org.neptrueworks.xenohermes.domain.interlocution.mention.MessageIndividualMention
import org.neptrueworks.xenohermes.domain.interlocution.mention.events.MessageIndividualMentionedEvent
import org.neptrueworks.xenohermes.domain.interlocution.mention.exceptions.MessageNotMentionedIndividualException
import org.neptrueworks.xenohermes.domain.interlocution.mention.isNotMentioned
import org.neptrueworks.xenohermes.domain.interlocution.mention.params.MessageMentionAgent
import org.neptrueworks.xenohermes.domain.interlocution.mention.params.MessageMentionMentioner
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

internal data class MentionIndividualsCommand(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val agent: MessageMentionAgent,
    val mentioner: MessageMentionMentioner,
    val mentioned: MessageIndividualMention,
) : MessageCorrespondenceCommand

@Service
internal final class MentionIndividualsCommandHandler(
    private val engagementRepository: SocialEngagementRepositable,
    private val eventTrigger: DomainEventRaiseable<MessageCorrespondenceEvent>,
) : CommandHandler<MentionIndividualsCommand>() {
    public override fun handle(command: MentionIndividualsCommand) {
        if (command.mentioned.isNotMentioned())
            throw MessageNotMentionedIndividualException(command.conversationId, command.messageId, command.mentioner);
        val mentions = command.mentioned as MessageIndividualMention.Mentioned;
        
        val engagerAgent = SocialEngagementEngager(command.agent.identifier);
        // TODO
        this.engagementRepository.fetchByIdentifier(engagerAgent);
        this.eventTrigger.raise(MessageIndividualMentionedEvent.initialize(command));
    }
}
