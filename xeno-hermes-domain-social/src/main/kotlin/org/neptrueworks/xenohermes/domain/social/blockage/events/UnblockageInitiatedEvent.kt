package org.neptrueworks.xenohermes.domain.social.blockage.events

import org.neptrueworks.xenohermes.domain.social.blockage.commands.InitiateUnblockageCommand
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker

public data class UnblockageInitiatedEvent private constructor(
    val unblocker: SocialBlockageBlocker,
    val unblockee: SocialBlockageBlockee,
) : SocialBlockageEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: InitiateUnblockageCommand) = UnblockageInitiatedEvent(
            unblocker = command.unblocker,
            unblockee = command.unblockee,
        )
    }
}