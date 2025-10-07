package org.neptrueworks.xenohermes.contract.interlocution.correspondence

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.OneToMany
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageForwardPermission
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageLengthThreshold
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageMentionPermission
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageQuotationPermission
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageReactionPermission
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageScheme
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageSchemeIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageUnsendPermission
import org.neptrueworks.xenohermes.domain.interlocution.scheme.MessageUnsendWindow

@Entity
public interface MessageSchema : MessageScheme {
    @Id
    override val schemeId: MessageSchemeIdentifier
    override val unsendPermission: MessageUnsendPermission
    override val quotationPermission: MessageQuotationPermission
    override val reactionPermission: MessageReactionPermission
    override val forwardPermission: MessageForwardPermission
    override val mentionPermission: MessageMentionPermission
    override val unsendWindow: MessageUnsendWindow
    override val lengthThreshold: MessageLengthThreshold
    @OneToMany(mappedBy = "scheme")
    val correspondences: List<MessageCorrespondence>
}