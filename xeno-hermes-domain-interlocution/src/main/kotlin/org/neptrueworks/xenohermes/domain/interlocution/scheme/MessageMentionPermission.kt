package org.neptrueworks.xenohermes.domain.interlocution.scheme

public enum class MessageMentionPermission {
    FORBIDDEN,
    PERMITTED;
}

public inline fun MessageMentionPermission.isForbidden() = 
    this == MessageMentionPermission.FORBIDDEN;
public inline fun MessageMentionPermission.isPermitted() = 
    this == MessageMentionPermission.PERMITTED;
