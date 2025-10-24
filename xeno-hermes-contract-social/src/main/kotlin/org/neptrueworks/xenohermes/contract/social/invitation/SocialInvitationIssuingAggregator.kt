package org.neptrueworks.xenohermes.contract.social.invitation

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIssuingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationResponseAggregateRoot

internal final class SocialInvitationIssuingAggregator(
    base: SocialInvitation?,
    draft: SocialInvitingDraft = SocialInvitingDraft(DraftContext(null), base),
) : SocialInvitationIssuingAggregateRoot(), DraftSpi by draft, SocialInvitation by draft {
    internal final fun resolve() = this.__resolve() as SocialInvitation;
}