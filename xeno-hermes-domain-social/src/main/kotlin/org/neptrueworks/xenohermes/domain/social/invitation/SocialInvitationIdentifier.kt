package org.neptrueworks.xenohermes.domain.social.invitation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateIdentifier
import org.neptrueworks.xenohermes.domain.common.models.InlineClass


@InlineClass
public data class SocialInvitationIdentifier(val identifier: Long) : AggregateIdentifier