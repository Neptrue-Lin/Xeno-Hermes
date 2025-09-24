package org.neptrueworks.xenohermes.domain.interlocution.scheme

import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class MessageLength(val length: UInt) : Comparable<MessageLength> {
    public override fun compareTo(other: MessageLength) = 
        this.length.compareTo(other.length);
}

inline infix fun MessageLength.isOverlength(threshold: MessageLengthThreshold) = 
    threshold.isOverlength(this);
inline infix fun MessageLength.isNotOverlength(threshold: MessageLengthThreshold) = 
    threshold.isNotOverlength(this);
