package org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceUnsender

public final class ReceiverNotEngagedException internal constructor(
    val unsender: MessageCorrespondenceUnsender,
    val receiver: MessageCorrespondenceReceiver
) : MessageCorrespondenceException()