package org.neptrueworks.xenohermes.domain.social.invitation.params


public enum class SocialInvitationRevocationStatus {
    ENDURING,
    REVOKED;
}

public inline fun SocialInvitationRevocationStatus.isEnduring() = 
    this == SocialInvitationRevocationStatus.ENDURING;
public inline fun SocialInvitationRevocationStatus.isRevoked() = 
    this == SocialInvitationRevocationStatus.REVOKED;
