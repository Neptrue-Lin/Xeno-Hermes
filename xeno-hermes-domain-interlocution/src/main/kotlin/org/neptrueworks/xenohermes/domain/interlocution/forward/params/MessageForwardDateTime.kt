package org.neptrueworks.xenohermes.domain.interlocution.forward.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass
import java.time.LocalDateTime

@InlineClass
public data class MessageForwardDateTime(val forwardedAt: LocalDateTime)