package org.neptrueworks.xenohermes.domain.interlocution.scheme

import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class MessageLengthThreshold(val threshold: UInt) : Comparable<MessageLength> {
    public override fun compareTo(other: MessageLength) =
        this.threshold.compareTo(other.length);
}

public inline fun MessageLengthThreshold.isOverlength(length: MessageLength) = 
    length.length > this.threshold;
public inline fun MessageLengthThreshold.isNotOverlength(length: MessageLength) = 
    length.length <= this.threshold;