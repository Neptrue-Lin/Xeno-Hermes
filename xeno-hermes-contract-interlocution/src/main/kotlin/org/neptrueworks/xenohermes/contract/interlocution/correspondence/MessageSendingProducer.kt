package org.neptrueworks.xenohermes.contract.interlocution.correspondence

import org.neptrueworks.xenohermes.contract.interlocution.correspondence.MessageCorrespondenceDraft.`$`.produce
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifierGeneratable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageSendingAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageSendingFactory
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageSendDateTime
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageUnsendStatus
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationRepositable
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.springframework.stereotype.Component

@Component
internal final class MessageSendingProducer(
    protected override val identifierGenerator: MessageIdentifierGeneratable,
    protected override val engagementCatalogRepository: SocialEngagementCatalogingRepositable,
    protected override val moderationRepository: InterlocutionModerationRepositable
) : MessageSendingFactory() {
    protected override fun produceMessageCorrespondence(
        messageId: MessageIdentifier,
        conversationId: ConversationIdentifier,
        sender: MessageCorrespondenceSender,
        receiver: MessageCorrespondenceReceiver,
        scheme: MessageScheme,
        sendDateTime: MessageSendDateTime,
    ): MessageSendingAggregateRoot = MessageSendingAggregator(produce {
        this.messageId = messageId
        this.conversationId = conversationId
        this.sender = sender
        this.receiver = receiver
        this.scheme = scheme as MessageSchema
        this.unsendStatus = MessageUnsendStatus.NOT_UNSENT
        this.sendDateTime = sendDateTime
    })
}