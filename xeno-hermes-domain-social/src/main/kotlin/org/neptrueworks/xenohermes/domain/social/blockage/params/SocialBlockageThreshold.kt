package org.neptrueworks.xenohermes.domain.social.blockage.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class SocialBlockageThreshold(val maximumBlockage: Int);

public inline fun SocialBlockageThreshold.isMaximumBlockageExceeds(blockage: SocialBlockageCount) =
    this.maximumBlockage <= blockage.currentBlockage;