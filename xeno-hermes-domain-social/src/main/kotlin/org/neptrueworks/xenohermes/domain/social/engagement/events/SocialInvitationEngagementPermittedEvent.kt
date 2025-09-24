package org.neptrueworks.xenohermes.domain.social.engagement.events

import org.neptrueworks.xenohermes.domain.social.engagement.commands.PermitInvitationEngagementCommand
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public data class SocialInvitationEngagementPermittedEvent private constructor(
    val engager: SocialEngagementEngager,
) : SocialEngagementEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: PermitInvitationEngagementCommand) = SocialInvitationEngagementPermittedEvent(
            engager = command.engager,
        )
    }
}