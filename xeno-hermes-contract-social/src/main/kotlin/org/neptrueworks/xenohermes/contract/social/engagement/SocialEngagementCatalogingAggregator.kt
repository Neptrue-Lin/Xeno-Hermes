package org.neptrueworks.xenohermes.contract.social.engagement

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.neptrueworks.xenohermes.contract.social.engagement.params.SocialEngagementCataloger
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingAggregateRoot

internal final class SocialEngagementCatalogingAggregator(
    public override val engagementCataloging: SocialEngagementCataloger,
    base: SocialEngager?,
    draft: SocialEngagementDraft = SocialEngagementDraft(DraftContext(null), base),
) : SocialEngagementCatalogingAggregateRoot(), DraftSpi by draft, SocialEngagerDraft by draft