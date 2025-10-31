package org.neptrueworks.xenohermes.domain.social.engagement.events

import org.neptrueworks.xenohermes.domain.social.engagement.commands.ForbidInvitationEngagementCommand
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public data class InvitationEngagementForbiddenEvent private constructor(
    val engager: SocialEngagementEngager,
) : SocialEngagementEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: ForbidInvitationEngagementCommand) = InvitationEngagementForbiddenEvent(
            engager = command.engager,
        )
    }
}
