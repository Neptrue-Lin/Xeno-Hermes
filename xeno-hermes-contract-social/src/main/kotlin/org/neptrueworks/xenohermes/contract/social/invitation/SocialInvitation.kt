package org.neptrueworks.xenohermes.contract.social.invitation

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationAggregatable
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.params.*

internal typealias SocialInvitingDraft = SocialInvitationDraft.`$`.DraftImpl

@Entity
public interface SocialInvitation: SocialInvitationAggregatable {
    @Id
    override val invitationId: SocialInvitationIdentifier
    override val issuer: SocialInvitationIssuer
    override val agent: SocialInvitationAgent
    override val audience: SocialInvitationAudience
    override val activePeriod: SocialInvitationActivePeriod
    override val expiryPeriod: SocialInvitationExpiryPeriod
    override val revocationStatus: SocialInvitationRevocationStatus
    override val acceptanceStatus: SocialInvitationAcceptanceStatus
    override val issueDateTime: SocialInvitationIssueDateTime
}