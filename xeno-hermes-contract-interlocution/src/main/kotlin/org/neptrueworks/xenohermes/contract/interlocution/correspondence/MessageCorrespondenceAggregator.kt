package org.neptrueworks.xenohermes.contract.interlocution.correspondence

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageCorrespondenceAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageSendingAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForward

internal final class MessageCorrespondenceAggregator(
    base: MessageCorrespondence?,
    draft: MessageCorrespondingDraft = MessageCorrespondingDraft(DraftContext(null), base),
) : MessageCorrespondenceAggregateRoot(), DraftSpi by draft, MessageCorrespondenceDraft by draft