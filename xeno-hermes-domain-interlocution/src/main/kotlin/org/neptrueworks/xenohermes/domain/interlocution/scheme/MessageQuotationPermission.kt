package org.neptrueworks.xenohermes.domain.interlocution.scheme

public enum class MessageQuotationPermission {
    FORBIDDEN,
    PERMITTED;
}

public inline fun MessageQuotationPermission.isForbidden() = 
    this == MessageQuotationPermission.FORBIDDEN;
public inline fun MessageQuotationPermission.isPermitted() = 
    this == MessageQuotationPermission.PERMITTED;
