package org.neptrueworks.xenohermes.domain.interlocution.scheme

public enum class MessageReactionPermission {
    FORBIDDEN,
    PERMITTED;
}

public inline fun MessageReactionPermission.isForbidden() = 
    this == MessageReactionPermission.FORBIDDEN;
public inline fun MessageReactionPermission.isPermitted() = 
    this == MessageReactionPermission.PERMITTED;
