package org.neptrueworks.xenohermes.contract.social.invitation

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.neptrueworks.xenohermes.contract.social.invitation.SocialInvitationDraft.`$`.produce
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationResponseAggregateRoot
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIssuingFactory
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifierGenerator
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIssuingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAcceptanceStatus
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationActivePeriod
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAgent
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAudience
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationExpiryPeriod
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationIssuer
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevocationStatus
import org.springframework.stereotype.Component

@Component
internal final class SocialInvitationIssuingProducer(
    protected override val identifierGenerator: SocialInvitationIdentifierGenerator,
    protected override val engagementCatalogRepository: SocialEngagementCatalogingRepositable,
    protected override val blockageCatalogRepository: SocialBlockageCatalogingRepositable,
) : SocialInvitationIssuingFactory() {
    protected override fun produceSocialInvitation(
        invitationId: SocialInvitationIdentifier,
        issuer: SocialInvitationIssuer,
        agent: SocialInvitationAgent,
        audience: SocialInvitationAudience,
        activePeriod: SocialInvitationActivePeriod,
        expiryPeriod: SocialInvitationExpiryPeriod,
        revocationStatus: SocialInvitationRevocationStatus,
        acceptanceStatus: SocialInvitationAcceptanceStatus
    ): SocialInvitationIssuingAggregateRoot = SocialInvitationIssuingAggregator(produce {
        this.invitationId = invitationId;
        this.issuer = issuer;
        this.agent = agent;
        this.audience = audience;
        this.activePeriod = activePeriod;
        this.expiryPeriod = expiryPeriod;
        this.revocationStatus = revocationStatus;
        this.acceptanceStatus = acceptanceStatus;
    })
}