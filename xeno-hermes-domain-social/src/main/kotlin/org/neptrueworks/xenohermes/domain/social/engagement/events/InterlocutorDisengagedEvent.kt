package org.neptrueworks.xenohermes.domain.social.engagement.events

import org.neptrueworks.xenohermes.domain.social.engagement.commands.DisengageInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialDisengagementDateTime
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

public data class InterlocutorDisengagedEvent private constructor(
    val disengager: SocialEngagementEngager,
    val disengagee: SocialEngagementEngagee,
    val disengagementDateTime: SocialDisengagementDateTime
) : SocialEngagementEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: DisengageInterlocutorCommand) = InterlocutorDisengagedEvent(
            disengager = command.disengager,
            disengagee = command.disengagee,
            disengagementDateTime = command.disengagementDateTime
        )
    }
}
