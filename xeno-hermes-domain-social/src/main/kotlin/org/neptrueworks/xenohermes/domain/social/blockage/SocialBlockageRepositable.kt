package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker

public interface SocialBlockageRepositable : AggregateRepositable<SocialBlockageAggregateRoot> {
    public fun fetchByIdentifier(blocker: SocialBlockageBlocker): SocialBlockageAggregateRoot
}
