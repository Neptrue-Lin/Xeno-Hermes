package org.neptrueworks.xenohermes.domain.social.blockage.events

import org.neptrueworks.xenohermes.domain.social.blockage.commands.EstablishBlockageCommand
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageDateTime
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker

public data class BlockageEstablishedEvent private constructor(
    val blocker: SocialBlockageBlocker,
    val blockee: SocialBlockageBlockee,
    val blockageDateTime: SocialBlockageDateTime,
) : SocialBlockageEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: EstablishBlockageCommand) = BlockageEstablishedEvent(
            blocker = command.blocker,
            blockee = command.blockee,
            blockageDateTime = command.blockageDateTime,
        )
    }
}