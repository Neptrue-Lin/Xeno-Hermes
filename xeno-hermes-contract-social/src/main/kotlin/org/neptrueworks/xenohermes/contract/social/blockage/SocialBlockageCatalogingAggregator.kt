package org.neptrueworks.xenohermes.contract.social.blockage

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.neptrueworks.xenohermes.contract.social.blockage.params.SocialBlockageCataloger
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCatalog

internal final class SocialBlockageCatalogingAggregator(
    public override val blockageCataloging: SocialBlockageCataloger,
    base: SocialBlocker?,
    draft: SocialBlockageDraft = SocialBlockageDraft(DraftContext(null), base),
) : SocialBlockageCatalogingAggregateRoot(), DraftSpi by draft, SocialBlocker by draft

