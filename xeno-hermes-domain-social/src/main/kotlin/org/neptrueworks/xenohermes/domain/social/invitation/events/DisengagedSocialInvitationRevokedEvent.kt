package org.neptrueworks.xenohermes.domain.social.invitation.events

import org.neptrueworks.xenohermes.domain.common.event.DomainEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.InterlocutorDisengagedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public data class DisengagedSocialInvitationRevokedEvent private constructor(
    val disengager: SocialEngagementEngager,
    val disengagee: SocialEngagementEngagee,
) : DomainEvent {
    internal companion object Initializer {
        internal final inline fun initialize(event: InterlocutorDisengagedEvent) = DisengagedSocialInvitationRevokedEvent(
            disengager = event.disengager,
            disengagee = event.disengagee,
        )
    }
}