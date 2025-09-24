package org.neptrueworks.xenohermes.domain.social.invitation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAudience
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationIssuer

public interface SocialInvitationRepositable : AggregateRepositable<SocialInvitationAggregateRoot> {
    public fun fetchByIdentifier(invitationId: SocialInvitationIdentifier): SocialInvitationAggregateRoot
    public fun fetchPrevious(issuer: SocialInvitationIssuer, invitee: SocialInvitationAudience): SocialInvitationAggregateRoot
    public fun fetchAllPending(issuer: SocialInvitationIssuer) : Iterable<SocialInvitationAggregateRoot>
}
