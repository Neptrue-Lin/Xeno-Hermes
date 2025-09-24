package org.neptrueworks.xenohermes.domain.social.engagement.params

public enum class SocialRequestEngagementPrivilege {
    PERMITTED,
    FORBIDDEN;
}

public inline fun SocialRequestEngagementPrivilege.isPermitted() = 
    this == SocialRequestEngagementPrivilege.PERMITTED;
public inline fun SocialRequestEngagementPrivilege.isForbidden() = 
    this == SocialRequestEngagementPrivilege.FORBIDDEN;
