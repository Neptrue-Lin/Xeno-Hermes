package org.neptrueworks.xenohermes.domain.social.engagement.events

import org.neptrueworks.xenohermes.domain.social.engagement.commands.EngageInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public data class InterlocutorEngagedEvent private constructor(
    val engager: SocialEngagementEngager,
    val engagee: SocialEngagementEngagee,
) : SocialEngagementEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: EngageInterlocutorCommand) = InterlocutorEngagedEvent(
            engager = command.engager,
            engagee = command.engagee,
        )
    }
}
