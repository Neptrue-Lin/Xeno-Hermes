package org.neptrueworks.xenohermes.contract.interlocution.forward

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.neptrueworks.xenohermes.contract.interlocution.forward.params.MessageForwardIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDeparture
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDestination

@Entity
public interface MessageForwarding {
    @Id
    val forwardId: MessageForwardIdentifier
    val forwardingConversationId: ConversationIdentifier
    val forwardingMessageId: MessageIdentifier
    val forwardDestination: ForwardMessageDestination
    val forwardedConversationId: ConversationIdentifier
    val forwardedMessageId: MessageIdentifier
    val forwardDeparture: ForwardMessageDeparture
}