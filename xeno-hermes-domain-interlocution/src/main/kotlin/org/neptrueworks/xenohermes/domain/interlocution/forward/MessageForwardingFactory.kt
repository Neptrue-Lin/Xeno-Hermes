package org.neptrueworks.xenohermes.domain.interlocution.forward

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRootFactory
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifierGeneratable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.*
import org.neptrueworks.xenohermes.domain.interlocution.forward.commands.ForwardMessageCommand
import org.neptrueworks.xenohermes.domain.interlocution.forward.exceptions.*
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationRepositable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.isBanned
import org.neptrueworks.xenohermes.domain.interlocution.scheme.isForbidden
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.isNotEngaged

public abstract class MessageForwardingFactory (
    private val correspondenceRepository: MessageCorrespondenceRepositable,
    private val moderationRepository: InterlocutionModerationRepositable,
    private val engagementCatalogRepository: SocialEngagementCatalogingRepositable,
    private val identifierGenerator: MessageIdentifierGeneratable,
) : AggregateRootFactory() {
    internal final fun forwardMessage(command: ForwardMessageCommand): MessageCorrespondenceAggregateRoot {
        this.forwarderShouldEngageForwardAndDeparture(command);
        this.forwarderShouldNotBeBanned(command);
        this.forwardedMessageShouldBeSendable(command);
        
        // TODO: frequencyLimit
        val destinedMessageId = this.identifierGenerator.nextIdentifier(command.destinedConversationId, command.destinedMessageId);
        return produceMessageCorrespondence(
            messageId = destinedMessageId,
            conversationId = command.destinedConversationId,
            sender = MessageCorrespondenceSender(command.forwarder.identifier),
            receiver = MessageCorrespondenceReceiver(command.destination.identifier),
            unsendStatus = MessageUnsendStatus.NOT_UNSENT,
            sendDateTime = MessageSendDateTime(command.forwardDateTime.forwardedAt),
            forward = MessageForward.Forwarded(command.departedConversationId, command.departedMessageId, command.departure)
        )
    }

    private final fun forwarderShouldEngageForwardAndDeparture(command: ForwardMessageCommand) {
        val forwarder = command.forwarder;
        val departure = command.departure;
        val destination = command.destination;

        val engagerForwarder = SocialEngagementEngager(forwarder.identifier);
        val engageeDeparture = SocialEngagementEngagee(departure.identifier);
        val engageeDestination = SocialEngagementEngagee(destination.identifier);

        val forwarderEngagement = this.engagementCatalogRepository.fetchByIdentifier(engagerForwarder, engageeDeparture);
        if (forwarderEngagement.engagementCatalog.checkNonengagement(engageeDeparture).isNotEngaged())
            throw ForwardDepartureNotEngagedException(forwarder, departure);
        if (forwarderEngagement.engagementCatalog.checkNonengagement(engageeDestination).isNotEngaged())
            throw ForwardDestinationNotEngagedException(forwarder, destination);
    }

    private final fun forwarderShouldNotBeBanned(command: ForwardMessageCommand) {
        val forwarder = command.forwarder;
        val departure = command.departure;
        val destination = command.destination;
        
        val destinationAgent = InterlocutionModerationAgent(destination.identifier);
        val participant = InterlocutionParticipant(forwarder.identifier);

        val moderation = this.moderationRepository.fetchByIdentifier(destinationAgent, participant);
        if (moderation.banCatalog[participant].isBanned())
            throw ForwarderBannedException(forwarder, departure);
    }

    private final fun forwardedMessageShouldBeSendable(command: ForwardMessageCommand) {
        val forwarder = command.forwarder;
        val departedConversationId = command.departedConversationId;
        val departedMessageId = command.departedMessageId;

        val departedMessage = this.correspondenceRepository.fetchByIdentifier(departedConversationId, departedMessageId);
        if (departedMessage.unsendStatus.isUnsent())
            throw ForwardMessageAlreadyUnsentException(departedConversationId, departedMessageId, forwarder);
        if (departedMessage.scheme.forwardPermission.isForbidden())
            throw MessageForwardForbiddenException(departedConversationId, departedMessageId, forwarder);
    }

    protected abstract fun produceMessageCorrespondence(
        messageId: MessageIdentifier,
        conversationId: ConversationIdentifier,
        sender: MessageCorrespondenceSender,
        receiver: MessageCorrespondenceReceiver,
        unsendStatus: MessageUnsendStatus,
        sendDateTime: MessageSendDateTime,
        forward: MessageForward
    ): MessageCorrespondenceAggregateRoot
}