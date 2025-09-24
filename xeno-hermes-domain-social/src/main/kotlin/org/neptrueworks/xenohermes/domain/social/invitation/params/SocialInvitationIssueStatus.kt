package org.neptrueworks.xenohermes.domain.social.invitation.params

public enum class SocialInvitationIssueStatus {
    ISSUED,
    NOT_ISSUED;
}

public inline fun SocialInvitationIssueStatus.isIssued() = 
    this == SocialInvitationIssueStatus.ISSUED;
public inline fun SocialInvitationIssueStatus.isNotIssued() = 
    this == SocialInvitationIssueStatus.NOT_ISSUED;
