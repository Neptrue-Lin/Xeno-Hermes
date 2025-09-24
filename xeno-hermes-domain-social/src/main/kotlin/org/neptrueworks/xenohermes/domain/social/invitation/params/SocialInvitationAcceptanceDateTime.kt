package org.neptrueworks.xenohermes.domain.social.invitation.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass
import java.time.LocalDateTime

@InlineClass
public data class SocialInvitationAcceptanceDateTime(val invokedAt: LocalDateTime);