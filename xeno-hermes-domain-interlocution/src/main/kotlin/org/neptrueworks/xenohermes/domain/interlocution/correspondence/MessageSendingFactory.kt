package org.neptrueworks.xenohermes.domain.interlocution.correspondence

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRootFactory
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.commands.SendMessageFacadeCommand
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageOverlengthException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageReceiverNotEngagedException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.SenderBannedException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageSendDateTime
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationRepositable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.isBanned
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme
import org.neptrueworks.xenohermes.domain.interlocution.scheme.isOverlength
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isNotEngaged

public abstract class MessageSendingFactory: AggregateRootFactory() {
    protected abstract val identifierGenerator: MessageIdentifierGeneratable
    protected abstract val engagementCatalogRepository: SocialEngagementCatalogingRepositable
    protected abstract val moderationRepository: InterlocutionModerationRepositable
            
    internal final fun sendMessage(command: SendMessageFacadeCommand): MessageSendingAggregateRoot {
        val sender = command.sender;
        val receiver = command.receiver;
        val conversation = command.conversationId;

        val engagerSender = SocialEngagementEngager(sender.identifier);
        val engageeReceiver = SocialEngagementEngagee(receiver.identifier);
        
        val destinationAgent = InterlocutionModerationAgent(receiver.identifier);
        val participant = InterlocutionParticipant(sender.identifier);

        val senderEngagement = this.engagementCatalogRepository.fetchByIdentifier(engagerSender, engageeReceiver);
        if (senderEngagement.engagementCatalog.checkNonengagement(engageeReceiver).isNotEngaged())
            throw MessageReceiverNotEngagedException(sender, receiver);

        val moderation = this.moderationRepository.fetchByIdentifier(destinationAgent, participant);
        if (moderation.banCatalog[participant].isBanned())
            throw SenderBannedException(sender, receiver);
            
        if (command.length isOverlength command.scheme.lengthThreshold) 
            throw MessageOverlengthException(conversation, sender);
        
        return produceMessageCorrespondence(
            messageId = this.identifierGenerator.nextIdentifier(conversation),
            conversationId = command.conversationId,
            sender = command.sender,
            receiver = command.receiver,
            scheme = command.scheme,
            sendDateTime = command.sendDateTime
        );
    }
    
    protected abstract fun produceMessageCorrespondence(
        messageId: MessageIdentifier,
        conversationId: ConversationIdentifier,
        sender: MessageCorrespondenceSender,
        receiver: MessageCorrespondenceReceiver,
        scheme: MessageScheme,
        sendDateTime: MessageSendDateTime
    ): MessageSendingAggregateRoot
}
