package org.neptrueworks.xenohermes.domain.social.invitation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAcceptanceStatus
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationActivePeriod
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAgent
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAudience
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationExpiryPeriod
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationIssueDateTime
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationIssuer
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevocationStatus

public abstract class SocialInvitationIssuingAggregateRoot : AggregateRoot(), SocialInvitationAggregatable {
    public abstract override val invitationId: SocialInvitationIdentifier
    public abstract override val issuer: SocialInvitationIssuer
    public abstract override val agent: SocialInvitationAgent
    public abstract override val audience: SocialInvitationAudience
    public abstract override val activePeriod: SocialInvitationActivePeriod
    public abstract override val expiryPeriod: SocialInvitationExpiryPeriod
    public abstract override val revocationStatus: SocialInvitationRevocationStatus
    public abstract override val acceptanceStatus: SocialInvitationAcceptanceStatus
    public abstract override val issueDateTime: SocialInvitationIssueDateTime
}