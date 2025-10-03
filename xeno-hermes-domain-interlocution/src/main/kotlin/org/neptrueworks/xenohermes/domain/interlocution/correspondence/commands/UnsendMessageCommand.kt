package org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.events.MessageCorrespondenceEvent
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.events.MessageUnsentEvent
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.ReceiverNotEngagedException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceUnsender
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageUnsendDateTime
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isNotEngaged
import org.springframework.stereotype.Service

public data class UnsendMessageCommand(
    val conversationId: ConversationIdentifier,
    val messageId: MessageIdentifier,
    val unsender: MessageCorrespondenceUnsender,
    val receiver: MessageCorrespondenceReceiver,
    val unsendDateTime: MessageUnsendDateTime
) : MessageCorrespondenceCommand

@Service
public final class UnsendMessageCommandHandler(
    private val correspondenceRepository: MessageCorrespondenceRepositable,
    private val engagementCatalogRepository: SocialEngagementCatalogingRepositable,
    private val eventTrigger: DomainEventRaiseable<MessageCorrespondenceEvent>
) : CommandHandler<UnsendMessageCommand>() {
    public override fun handle(command: UnsendMessageCommand) {
        val engagerUnsender = SocialEngagementEngager(command.unsender.identifier);
        val engageeReceiver = SocialEngagementEngagee(command.unsender.identifier);
        
        val engagement = this.engagementCatalogRepository.fetchByIdentifier(engagerUnsender, engageeReceiver);
        if (engagement.engagementCatalog.checkNonengagement(engageeReceiver).isNotEngaged())
            throw ReceiverNotEngagedException(command.unsender, command.receiver);
        
        val correspondence = this.correspondenceRepository.fetchByIdentifier(command.conversationId, command.messageId);
        correspondence.unsendMessage(command);
        this.eventTrigger.raise(MessageUnsentEvent.initialize(command));
        this.correspondenceRepository.reposit(correspondence);
    }
}
