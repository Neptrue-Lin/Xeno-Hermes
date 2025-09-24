package org.neptrueworks.xenohermes.domain.interlocution.correspondence.params

public enum class MessageSendStatus {
    NOT_SENT,
    SENT;
}

public inline fun MessageSendStatus.isSent() = 
    this == MessageSendStatus.SENT;
public inline fun MessageSendStatus.isNotSent() = 
    this == MessageSendStatus.NOT_SENT;
