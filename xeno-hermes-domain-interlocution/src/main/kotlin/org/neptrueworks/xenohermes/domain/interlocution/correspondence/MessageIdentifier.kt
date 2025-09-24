package org.neptrueworks.xenohermes.domain.interlocution.correspondence

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateIdentifier

import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class MessageIdentifier(val identifier: Long) : AggregateIdentifier
