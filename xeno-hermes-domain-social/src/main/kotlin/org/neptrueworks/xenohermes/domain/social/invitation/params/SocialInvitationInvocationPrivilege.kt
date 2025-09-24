package org.neptrueworks.xenohermes.domain.social.invitation.params


public enum class SocialInvitationInvocationPrivilege {
    FORBIDDEN,
    PERMITTED;
}

public inline fun SocialInvitationInvocationPrivilege.isPermitted() = 
    this == SocialInvitationInvocationPrivilege.PERMITTED;
public inline fun SocialInvitationInvocationPrivilege.isForbidden() = 
    this == SocialInvitationInvocationPrivilege.FORBIDDEN;
