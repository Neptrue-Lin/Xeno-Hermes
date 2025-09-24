package org.neptrueworks.xenohermes.domain.social.request.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass
import java.time.LocalDateTime

@InlineClass
public data class SocialRequestRejectionDateTime(val rejectedAt: LocalDateTime);