package org.neptrueworks.xenohermes.domain.interlocution.correspondence

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRootFactory
import org.neptrueworks.xenohermes.domain.common.models.DomainService
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.SendMessageFacadeCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageOverlengthException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageReceiverNotEngaged
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.SenderBannedException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageSendDateTime
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageUnsendStatus
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationBanningRepositable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.isBanned
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme
import org.neptrueworks.xenohermes.domain.interlocution.scheme.isOverlength
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isNotEngaged

public abstract class MessageSendingFactory(
    private val identifierGenerator: MessageIdentifierGeneratable,
    private val engagementCatalogRepository: SocialEngagementCatalogingRepositable,
    private val moderationBanRepository: InterlocutionModerationBanningRepositable,
) : AggregateRootFactory(), DomainService {
    internal final fun sendMessage(command: SendMessageFacadeCommand): MessageCorrespondenceAggregateRoot {
        val sender = command.sender;
        val receiver = command.receiver;
        val clientMessageId = command.messageId;
        val conversation = command.conversationId;

        val engagerSender = SocialEngagementEngager(sender.identifier);
        val engageeReceiver = SocialEngagementEngagee(receiver.identifier);
        
        val destinationAgent = InterlocutionModerationAgent(receiver.identifier);
        val participant = InterlocutionParticipant(sender.identifier);

        val senderEngagement = this.engagementCatalogRepository.fetchByIdentifier(engagerSender, engageeReceiver);
        if (senderEngagement.engagementCatalog.checkNonengagement(engageeReceiver).isNotEngaged())
            throw MessageReceiverNotEngaged(sender, receiver);

        val moderation = this.moderationBanRepository.fetchByIdentifier(destinationAgent);
        if (moderation.interlocutionBans[participant].isBanned())
            throw SenderBannedException(sender, receiver);
            
        if (command.length isOverlength command.scheme.lengthThreshold) 
            throw MessageOverlengthException(conversation, clientMessageId, sender);
        
        return produceMessageCorrespondence(
            messageId = this.identifierGenerator.nextIdentifier(conversation, clientMessageId),
            conversationId = command.conversationId,
            sender = command.sender,
            receiver = command.receiver,
            scheme = command.scheme,
            unsendStatus = MessageUnsendStatus.NOT_UNSENT,
            sendDateTime = command.sendDateTime
        );
    }
    
    protected abstract fun produceMessageCorrespondence(
        messageId: MessageIdentifier,
        conversationId: ConversationIdentifier,
        sender: MessageCorrespondenceSender,
        receiver: MessageCorrespondenceReceiver,
        scheme: MessageScheme,
        unsendStatus: MessageUnsendStatus,
        sendDateTime: MessageSendDateTime
    ): MessageCorrespondenceAggregateRoot
}
