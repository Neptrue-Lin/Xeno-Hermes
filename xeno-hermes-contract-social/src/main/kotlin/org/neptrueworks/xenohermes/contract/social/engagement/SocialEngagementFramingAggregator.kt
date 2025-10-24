package org.neptrueworks.xenohermes.contract.social.engagement

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementFramingAggregateRoot

internal final class SocialEngagementFramingAggregator(
    base: SocialEngager?,
    draft: SocialEngagementDraft = SocialEngagementDraft(DraftContext(null), base),
) : SocialEngagementFramingAggregateRoot(), DraftSpi by draft, SocialEngager by draft {
    internal final fun resolve() = this.__resolve() as SocialEngager;
}