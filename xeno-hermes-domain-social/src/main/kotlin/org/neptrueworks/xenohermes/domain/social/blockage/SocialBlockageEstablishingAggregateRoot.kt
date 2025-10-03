package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold

public abstract class SocialBlockageEstablishingAggregateRoot: AggregateRoot(), SocialBlockageAggregatable {
    public abstract override val blocker: SocialBlockageBlocker
    public abstract override val blockageThreshold: SocialBlockageThreshold
}
