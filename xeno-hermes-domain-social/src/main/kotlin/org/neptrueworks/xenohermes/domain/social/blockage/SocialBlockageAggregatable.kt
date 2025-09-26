package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCatalog
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCount
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold

public interface SocialBlockageAggregatable : Aggregatable {
    val blocker: SocialBlockageBlocker
    val blockageThreshold: SocialBlockageThreshold
}