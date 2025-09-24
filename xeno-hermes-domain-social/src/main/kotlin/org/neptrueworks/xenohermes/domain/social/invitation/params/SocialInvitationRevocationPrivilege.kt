package org.neptrueworks.xenohermes.domain.social.invitation.params


public enum class SocialInvitationRevocationPrivilege {
    FORBIDDEN,
    PERMITTED;
}

public inline fun SocialInvitationRevocationPrivilege.isPermitted() = 
    this == SocialInvitationRevocationPrivilege.PERMITTED;
public inline fun SocialInvitationRevocationPrivilege.isForbidden() = 
    this == SocialInvitationRevocationPrivilege.FORBIDDEN;
