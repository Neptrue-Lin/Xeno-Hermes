package org.neptrueworks.xenohermes.contract.social.invitation

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationResponseAggregateRoot

internal final class SocialInvitationResponseAggregator(
    base: SocialInvitation?,
    draft: SocialInvitingDraft = SocialInvitingDraft(DraftContext(null), base),
) : SocialInvitationResponseAggregateRoot(), DraftSpi by draft, SocialInvitationDraft by draft