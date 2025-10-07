package org.neptrueworks.xenohermes.contract.interlocution.moderation

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.neptrueworks.xenohermes.contract.interlocution.moderation.params.InterlocutionBanCataloger
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBanCatalog

internal final class InterlocutionModerationAggregator(
    public override val banCataloging: InterlocutionBanCataloger,
    base: InterlocutionModeration?,
    draft: InterlocutionModeratingDraft = InterlocutionModeratingDraft(DraftContext(null), base),
) : InterlocutionModerationAggregateRoot(), DraftSpi by draft, InterlocutionModerationDraft by draft