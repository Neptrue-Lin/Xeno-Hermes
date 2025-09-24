package org.neptrueworks.xenohermes.domain.social.invitation.params


public enum class SocialInvitationAcceptanceStatus {
    NOT_ACCEPTED,
    ACCEPTED;
}

public inline fun SocialInvitationAcceptanceStatus.isAccepted() = 
    this == SocialInvitationAcceptanceStatus.ACCEPTED;
public inline fun SocialInvitationAcceptanceStatus.isNotAccepted() = 
    this == SocialInvitationAcceptanceStatus.NOT_ACCEPTED;
