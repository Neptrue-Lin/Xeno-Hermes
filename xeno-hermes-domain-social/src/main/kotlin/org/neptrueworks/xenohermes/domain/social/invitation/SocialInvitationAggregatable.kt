package org.neptrueworks.xenohermes.domain.social.invitation

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.social.invitation.params.*


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
//    val revocationPrivilege: SocialInvitationRevocationPrivilege
//    val invocationPrivilege: SocialInvitationInvocationPrivilege
}