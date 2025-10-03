package org.neptrueworks.xenohermes.contract.social.blockage

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageEstablishingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCatalog

internal final class SocialBlockageEstablishingAggregator(
    base: SocialBlocker?,
    draft: SocialBlockageDraft = SocialBlockageDraft(DraftContext(null), base),
) : SocialBlockageEstablishingAggregateRoot(), DraftSpi by draft, SocialBlocker by draft {
    internal final fun resolve() = this.__resolve() as SocialBlocker;
}

