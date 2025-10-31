package org.neptrueworks.xenohermes.domain.social.engagement.events

import org.neptrueworks.xenohermes.domain.social.engagement.commands.EstablishEngagementCommand
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementMethod

public data class EngagementEstablishedEvent private constructor(
    val engager: SocialEngagementEngager,
    val engagee: SocialEngagementEngagee,
    val method: SocialEngagementMethod
) : SocialEngagementEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: EstablishEngagementCommand) = EngagementEstablishedEvent(
            engager = command.engager,
            engagee = command.engagee,
            method = command.method
        )
    }
}
