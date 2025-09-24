package org.neptrueworks.xenohermes.domain.social.request.params


public enum class SocialResponsePrivilege {
    FORBIDDEN,
    PERMITTED;
}

public inline fun SocialResponsePrivilege.isPermitted() = 
    this == SocialResponsePrivilege.PERMITTED;
public inline fun SocialResponsePrivilege.isForbidden() = 
    this == SocialResponsePrivilege.FORBIDDEN;