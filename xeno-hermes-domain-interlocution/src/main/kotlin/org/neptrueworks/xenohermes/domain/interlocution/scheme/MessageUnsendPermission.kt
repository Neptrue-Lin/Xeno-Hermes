package org.neptrueworks.xenohermes.domain.interlocution.scheme

public enum class MessageUnsendPermission {
    FORBIDDEN,
    PERMITTED;
 }

public inline fun MessageUnsendPermission.isForbidden() = 
    this == MessageUnsendPermission.FORBIDDEN;
public inline fun MessageUnsendPermission.isPermitted() = 
    this == MessageUnsendPermission.PERMITTED;
