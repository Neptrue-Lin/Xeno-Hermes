package org.neptrueworks.xenohermes.domain.social.invitation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAudience
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationIssuer

public interface SocialInvitationResponseRepositable : AggregateRepositable<SocialInvitationResponseAggregateRoot> {
    public fun fetchByIdentifier(invitationId: SocialInvitationIdentifier): SocialInvitationResponseAggregateRoot
    public fun fetchPrevious(issuer: SocialInvitationIssuer, invitee: SocialInvitationAudience): SocialInvitationAggregatable
    public fun fetchAllPending(issuer: SocialInvitationIssuer) : Iterable<SocialInvitationAggregatable>
}
