package org.neptrueworks.xenohermes.domain.social.engagement.events

import org.neptrueworks.xenohermes.domain.social.engagement.commands.PermitRequestEngagementCommand
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public data class RequestEngagementPermittedEvent private constructor(
    val engager: SocialEngagementEngager,
) : SocialEngagementEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: PermitRequestEngagementCommand) = RequestEngagementPermittedEvent(
            engager = command.engager,
        )
    }
}
