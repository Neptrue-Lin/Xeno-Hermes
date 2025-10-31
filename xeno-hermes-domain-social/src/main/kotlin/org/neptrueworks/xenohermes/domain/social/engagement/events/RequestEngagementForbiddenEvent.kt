package org.neptrueworks.xenohermes.domain.social.engagement.events

import org.neptrueworks.xenohermes.domain.social.engagement.commands.ForbidRequestEngagementCommand
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public data class RequestEngagementForbiddenEvent private constructor(
    val engager: SocialEngagementEngager,
) : SocialEngagementEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: ForbidRequestEngagementCommand) = RequestEngagementForbiddenEvent(
            engager = command.engager,
        )
    }
}
