package org.neptrueworks.xenohermes.domain.interlocution.correspondence.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass
import java.time.LocalDateTime

@InlineClass
public data class MessageRevisionDateTime(val revisedAt: LocalDateTime);
