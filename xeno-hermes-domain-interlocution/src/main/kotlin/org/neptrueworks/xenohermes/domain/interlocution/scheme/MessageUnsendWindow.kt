package org.neptrueworks.xenohermes.domain.interlocution.scheme

import org.neptrueworks.xenohermes.domain.common.models.InlineClass
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageSendDateTime
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageUnsendDateTime
import java.time.Duration

@InlineClass
public data class MessageUnsendWindow(val window: Duration);


public inline fun MessageUnsendWindow.isUnsendWindowPast(sendDateTime: MessageSendDateTime, unsendDateTime: MessageUnsendDateTime) =
    unsendDateTime.unsentAt.isAfter(sendDateTime.sentAt + this.window);