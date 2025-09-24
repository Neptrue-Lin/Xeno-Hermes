package org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceReceiver
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageCorrespondenceSender

public final class MessageReceiverNotEngaged internal constructor(
    val sender: MessageCorrespondenceSender,
    val receiver: MessageCorrespondenceReceiver
) : MessageCorrespondenceException()