package org.neptrueworks.xenohermes.domain.social.blockage.events

import org.neptrueworks.xenohermes.domain.social.blockage.commands.UnblockInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker

public data class InterlocutorUnblockedEvent private constructor(
    val unblocker: SocialBlockageBlocker,
    val unblockee: SocialBlockageBlockee,
) : SocialBlockageEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: UnblockInterlocutorCommand) = InterlocutorUnblockedEvent(
            unblocker = command.unblocker,
            unblockee = command.unblockee,
        )
    }
}