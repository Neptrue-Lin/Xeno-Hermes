package org.neptrueworks.xenohermes.domain.social.engagement.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass
import java.time.LocalDateTime

@InlineClass
public data class SocialDisengagementDateTime(val disengagedAt: LocalDateTime)