package org.neptrueworks.xenohermes.contract.interlocution.correspondence

import org.babyfish.jimmer.Formula
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.KeyUniqueConstraint
import org.babyfish.jimmer.sql.ManyToOne
import org.babyfish.jimmer.sql.OneToMany
import org.neptrueworks.xenohermes.contract.interlocution.correspondence.params.MessageCorrespondenceIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceAggregatable
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForward
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageSendDateTime
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageUnsendStatus
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDeparture

internal typealias MessageCorrespondingDraft = MessageCorrespondenceDraft.`$`.DraftImpl;

@Entity
@KeyUniqueConstraint
public interface MessageCorrespondence : MessageCorrespondenceAggregatable {
    @Id
    val correspondenceId: MessageCorrespondenceIdentifier
    @Key
    override val conversationId: ConversationIdentifier
    @Key
    override val messageId: MessageIdentifier
    override val sender: MessageCorrespondenceSender
    override val receiver: MessageCorrespondenceReceiver
    override val unsendStatus: MessageUnsendStatus
    @Formula(dependencies = ["forwardedFrom", "forwardedFrom.messageId", "forwardedFrom.conversationId", "forwardedFrom.receiver"])
    override val forward get() = 
        if (this.forwardedFrom == null) 
             MessageForward.NotForwarded 
        else MessageForward.Forwarded(
            forwardedConversationId =  this.forwardedFrom!!.conversationId, 
            forwardedMessageId = this.forwardedFrom!!.messageId, 
            forwardDeparture = ForwardMessageDeparture(this.forwardedFrom!!.receiver.identifier)
        )
    override val sendDateTime: MessageSendDateTime
    @ManyToOne
    override val scheme: MessageSchema
    @ManyToOne
    val forwardedFrom: MessageCorrespondence?
    @OneToMany(mappedBy = "forwardedFrom")
    val forwardedTo: List<MessageCorrespondence>
}