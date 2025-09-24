package org.neptrueworks.xenohermes.domain.social.request.params


public enum class SocialRequestRevocationStatus {
    ENDURING,
    REVOKED;
}

public inline fun SocialRequestRevocationStatus.isEnduring() = this == SocialRequestRevocationStatus.ENDURING;
public inline fun SocialRequestRevocationStatus.isRevoked() =  this == SocialRequestRevocationStatus.REVOKED;
