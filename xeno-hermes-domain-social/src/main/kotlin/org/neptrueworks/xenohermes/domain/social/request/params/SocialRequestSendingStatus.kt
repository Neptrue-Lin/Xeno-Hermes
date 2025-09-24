package org.neptrueworks.xenohermes.domain.social.request.params

public enum class SocialRequestSendingStatus {
    SENT,
    NOT_SENT;
}

public inline fun SocialRequestSendingStatus.isSent() = this == SocialRequestSendingStatus.SENT;
public inline fun SocialRequestSendingStatus.isNotSent() = this == SocialRequestSendingStatus.NOT_SENT;