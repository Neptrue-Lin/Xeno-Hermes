package org.neptrueworks.xenohermes.domain.social.blockage.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass
import java.time.LocalDateTime

@InlineClass
public data class SocialBlockageDateTime(val blockedAt: LocalDateTime);
