package org.neptrueworks.xenohermes.domain.social.engagement.params

public enum class SocialInvitationEngagementPrivilege {
    PERMITTED,
    FORBIDDEN;
}

public inline fun SocialInvitationEngagementPrivilege.isPermitted() = 
    this == SocialInvitationEngagementPrivilege.PERMITTED;
public inline fun SocialInvitationEngagementPrivilege.isForbidden() = 
    this == SocialInvitationEngagementPrivilege.FORBIDDEN;
