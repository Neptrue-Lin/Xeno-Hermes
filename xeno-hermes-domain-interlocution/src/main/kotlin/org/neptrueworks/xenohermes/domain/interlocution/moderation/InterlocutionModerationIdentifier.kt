package org.neptrueworks.xenohermes.domain.interlocution.moderation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateIdentifier


import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class InterlocutionModerationIdentifier(val identifier: Long) : AggregateIdentifier