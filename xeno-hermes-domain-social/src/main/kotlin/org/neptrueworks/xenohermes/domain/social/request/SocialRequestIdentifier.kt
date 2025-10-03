package org.neptrueworks.xenohermes.domain.social.request

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateIdentifier
import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class SocialRequestIdentifier(val identifier: Long) : AggregateIdentifier