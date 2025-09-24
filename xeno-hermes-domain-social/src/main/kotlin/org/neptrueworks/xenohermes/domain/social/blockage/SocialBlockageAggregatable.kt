package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageManifest
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold

public interface SocialBlockageAggregatable : Aggregatable {
    val blocker: SocialBlockageBlocker
    val blockageManifest: SocialBlockageManifest
    val blockageThreshold: SocialBlockageThreshold
}