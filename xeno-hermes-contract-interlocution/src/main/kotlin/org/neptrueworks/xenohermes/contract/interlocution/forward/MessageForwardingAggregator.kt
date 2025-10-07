package org.neptrueworks.xenohermes.contract.interlocution.forward

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.neptrueworks.xenohermes.contract.interlocution.correspondence.MessageCorrespondence
import org.neptrueworks.xenohermes.contract.interlocution.correspondence.MessageCorrespondenceDraft
import org.neptrueworks.xenohermes.contract.interlocution.correspondence.MessageCorrespondingDraft
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForward
import org.neptrueworks.xenohermes.domain.interlocution.forward.MessageForwardingAggregateRoot

internal final class MessageForwardingAggregator(
    base: MessageCorrespondence?,
    draft: MessageCorrespondingDraft = MessageCorrespondingDraft(DraftContext(null), base),
) : MessageForwardingAggregateRoot(), DraftSpi by draft, MessageCorrespondenceDraft by draft {
    internal final fun resolve() = this.__resolve() as MessageCorrespondence;
}