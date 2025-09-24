package org.neptrueworks.xenohermes.domain.social.request.params

// TODO: Nullability mapping to NOT_RESPONDED
public enum class SocialResponseStatus {
    NOT_RESPONDED,
    ACCEPTED,
    REJECTED;
}

public inline fun SocialResponseStatus.isNotResponded() = this == SocialResponseStatus.NOT_RESPONDED;
public inline fun SocialResponseStatus.isResponded() = this != SocialResponseStatus.NOT_RESPONDED;
public inline fun SocialResponseStatus.isAccepted() = this == SocialResponseStatus.ACCEPTED;
public inline fun SocialResponseStatus.isRejected() = this == SocialResponseStatus.REJECTED;