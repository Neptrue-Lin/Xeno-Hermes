package org.neptrueworks.xenohermes.domain.social.request.params

public enum class SocialRequestRevocationPrivilege {
    FORBIDDEN,
    PERMITTED;
}

public inline fun SocialRequestRevocationPrivilege.isPermitted() = this == SocialRequestRevocationPrivilege.PERMITTED;
public inline fun SocialRequestRevocationPrivilege.isForbidden() = this == SocialRequestRevocationPrivilege.FORBIDDEN;
