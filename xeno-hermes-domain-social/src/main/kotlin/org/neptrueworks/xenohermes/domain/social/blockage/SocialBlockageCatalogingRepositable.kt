package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker

public interface SocialBlockageCatalogingRepositable : AggregateRepositable<SocialBlockageCatalogingAggregateRoot> {
    public fun fetchByIdentifier(blocker: SocialBlockageBlocker, blockee: SocialBlockageBlockee): SocialBlockageCatalogingAggregateRoot
}
