package org.neptrueworks.xenohermes.domain.social.invitation.events

import org.neptrueworks.xenohermes.domain.common.event.DomainEvent
import org.neptrueworks.xenohermes.domain.social.blockage.events.BlockageEstablishedEvent
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker

public data class BlockedSocialInvitationRevokedEvent private constructor(
    val blocker: SocialBlockageBlocker,
    val blockee: SocialBlockageBlockee,
) : DomainEvent {
    internal companion object Initializer {
        internal final inline fun initialize(event: BlockageEstablishedEvent) = BlockedSocialInvitationRevokedEvent(
            blocker = event.blocker,
            blockee = event.blockee,
        )
    }
}
