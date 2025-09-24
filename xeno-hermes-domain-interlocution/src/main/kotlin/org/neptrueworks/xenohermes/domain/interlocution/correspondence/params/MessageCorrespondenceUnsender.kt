package org.neptrueworks.xenohermes.domain.interlocution.correspondence.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class MessageCorrespondenceUnsender(val identifier: Long)

public inline fun MessageCorrespondenceUnsender.isNotSender(sender: MessageCorrespondenceSender) =
    this.identifier != sender.identifier;

public inline fun MessageCorrespondenceUnsender.isSender(sender: MessageCorrespondenceSender) =
    this.identifier == sender.identifier;