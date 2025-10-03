package org.neptrueworks.xenohermes.domain.social.invitation

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.social.invitation.params.*
import java.time.LocalDateTime


public interface SocialInvitationAggregatable : Aggregatable {
    val invitationId: SocialInvitationIdentifier
    val issuer: SocialInvitationIssuer
    val agent: SocialInvitationAgent
    val audience: SocialInvitationAudience
    val activePeriod: SocialInvitationActivePeriod
    val expiryPeriod: SocialInvitationExpiryPeriod
    val revocationStatus: SocialInvitationRevocationStatus
    val acceptanceStatus: SocialInvitationAcceptanceStatus
    val issueDateTime: SocialInvitationIssueDateTime
}

public inline fun SocialInvitationAggregatable.isValid(currentDateTime: LocalDateTime) =
    this.activePeriod.isActivated(currentDateTime)
            && this.expiryPeriod.isUnexpired(currentDateTime)
            && this.revocationStatus.isEnduring()

public inline fun SocialInvitationAggregatable.isInvalid(currentDateTime: LocalDateTime) =
    this.activePeriod.isNotActivated(currentDateTime)
            || this.expiryPeriod.isExpired(currentDateTime)
            || this.revocationStatus.isRevoked()
