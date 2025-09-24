package org.neptrueworks.xenohermes.domain.social.invitation.params

import java.time.LocalDateTime

public sealed class SocialInvitationExpiryPeriod : Comparable<SocialInvitationExpiryPeriod> {
    public data object NonExpiring : SocialInvitationExpiryPeriod() {
        override fun compareTo(other: SocialInvitationExpiryPeriod) = when (other) {
            is NonExpiring -> 0
            is TemporaryUnexpired -> 1
        }
    }
    public data class TemporaryUnexpired(val expiredAt: LocalDateTime) : SocialInvitationExpiryPeriod() {
        override fun compareTo(other: SocialInvitationExpiryPeriod) = when (other) {
            is NonExpiring -> -1
            is TemporaryUnexpired -> this.expiredAt.compareTo(other.expiredAt)
        }
    }
}

public inline fun SocialInvitationExpiryPeriod.isExpired(localDateTime: LocalDateTime) = when (this) {
    is SocialInvitationExpiryPeriod.NonExpiring -> false
    is SocialInvitationExpiryPeriod.TemporaryUnexpired -> this.expiredAt.isBefore(localDateTime)
}

public inline fun SocialInvitationExpiryPeriod.isUnexpired(localDateTime: LocalDateTime) = !this.isExpired(localDateTime)