package org.neptrueworks.xenohermes.domain.interlocution.scheme

public enum class MessageForwardPermission {
    FORBIDDEN,
    PERMITTED;
}

public inline fun MessageForwardPermission.isForbidden() = 
    this == MessageForwardPermission.FORBIDDEN;
public inline fun MessageForwardPermission.isPermitted() = 
    this == MessageForwardPermission.PERMITTED;
