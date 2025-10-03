package org.neptrueworks.xenohermes.domain.social.invitation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAudience
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationIssuer

public interface SocialInvitationIssuingRepositable : AggregateRepositable<SocialInvitationIssuingAggregateRoot> {
}
