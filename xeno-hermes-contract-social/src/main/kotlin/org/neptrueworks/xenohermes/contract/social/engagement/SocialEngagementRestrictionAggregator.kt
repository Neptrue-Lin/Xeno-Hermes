package org.neptrueworks.xenohermes.contract.social.engagement

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRestrictionAggregateRoot

internal final class SocialEngagementRestrictionAggregator(
    base: SocialEngager?,
    draft: SocialEngagementDraft = SocialEngagementDraft(DraftContext(null), base),
) : SocialEngagementRestrictionAggregateRoot(), DraftSpi by draft, SocialEngagerDraft by draft {
    internal final fun resolve() = this.__resolve() as SocialEngager;
}