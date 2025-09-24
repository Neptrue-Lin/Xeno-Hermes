package org.neptrueworks.xenohermes.domain.interlocution.scheme

import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class MessageSchemeIdentifier(val identifier: Long) : Comparable<MessageSchemeIdentifier> {
    public override fun compareTo(other: MessageSchemeIdentifier) = 
        this.identifier.compareTo(other.identifier)
}