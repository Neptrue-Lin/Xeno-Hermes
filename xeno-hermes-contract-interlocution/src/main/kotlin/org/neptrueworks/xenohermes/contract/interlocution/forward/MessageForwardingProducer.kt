package org.neptrueworks.xenohermes.contract.interlocution.forward

import org.neptrueworks.xenohermes.contract.interlocution.correspondence.MessageCorrespondenceDraft.`$`.produce
import org.neptrueworks.xenohermes.contract.interlocution.correspondence.MessageSchema
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceRepositable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifierGeneratable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForward
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForwarder
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageSendDateTime
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageUnsendStatus
import org.neptrueworks.xenohermes.domain.interlocution.forward.MessageForwardingAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.forward.MessageForwardingFactory
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDeparture
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDestination
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.MessageForwardDateTime
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationRepositable
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.springframework.stereotype.Component

@Component
internal final class MessageForwardingProducer(
    protected override val correspondenceRepository: MessageCorrespondenceRepositable,
    protected override val moderationRepository: InterlocutionModerationRepositable,
    protected override val engagementCatalogRepository: SocialEngagementCatalogingRepositable,
    protected override val identifierGenerator: MessageIdentifierGeneratable,
) : MessageForwardingFactory() {
    protected override fun produceMessageCorrespondence(
        destinedMessageId: MessageIdentifier,
        destinedConversationId: ConversationIdentifier,
        destination: ForwardMessageDestination,
        forwarder: MessageForwarder,
        departedMessageId: MessageIdentifier,
        departedConversationId: ConversationIdentifier,
        departure: ForwardMessageDeparture,
        forwardDateTime: MessageForwardDateTime,
        scheme: MessageScheme,
    ): MessageForwardingAggregateRoot = MessageForwardingAggregator(produce {
        this.messageId = destinedMessageId
        this.conversationId = destinedConversationId
        this.sender = MessageCorrespondenceSender(forwarder.identifier)
        this.receiver = MessageCorrespondenceReceiver(destination.identifier)
        this.unsendStatus = MessageUnsendStatus.NOT_UNSENT
        this.sendDateTime = MessageSendDateTime(forwardDateTime.forwardedAt)
        this.scheme = scheme as MessageSchema
        this.forwardedFrom {
            this.conversationId = departedConversationId
            this.messageId = departedMessageId
        }
    })
}