package org.neptrueworks.xenohermes.domain.interlocution.correspondence.params

public enum class MessageUnsendStatus {
    NOT_UNSENT,
    UNSENT;
}

public inline fun MessageUnsendStatus.isNotUnsent() = 
    this == MessageUnsendStatus.NOT_UNSENT;
public inline fun MessageUnsendStatus.isUnsent() = 
    this == MessageUnsendStatus.UNSENT;
