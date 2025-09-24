package org.neptrueworks.xenohermes.domain.social.blockage.events

import org.neptrueworks.xenohermes.domain.social.blockage.commands.BlockInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.blockage.params.InterlocutorBlockageDateTime
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker

public data class InterlocutorBlockedEvent private constructor(
    val blocker: SocialBlockageBlocker,
    val blockee: SocialBlockageBlockee,
    val blockageDateTime: InterlocutorBlockageDateTime,
) : SocialBlockageEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: BlockInterlocutorCommand) = InterlocutorBlockedEvent(
            blocker = command.blocker,
            blockee = command.blockee,
            blockageDateTime = command.blockageDateTime,
        )
    }
}