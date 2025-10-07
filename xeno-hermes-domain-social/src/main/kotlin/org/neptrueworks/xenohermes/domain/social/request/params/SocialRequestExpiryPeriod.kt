package org.neptrueworks.xenohermes.domain.social.request.params

import java.time.LocalDateTime

public sealed class SocialRequestExpiryPeriod : Comparable<SocialRequestExpiryPeriod> {
    public data object NonExpiring : SocialRequestExpiryPeriod() {
        override fun compareTo(other: SocialRequestExpiryPeriod) = when (other) {
            is NonExpiring -> 0
            is TemporaryUnexpired -> 1
        }
    }
    public data class TemporaryUnexpired(val expiryPeriod: LocalDateTime) : SocialRequestExpiryPeriod() {
        override fun compareTo(other: SocialRequestExpiryPeriod) = when (other) {
            is NonExpiring -> -1
            is TemporaryUnexpired -> this.expiryPeriod.compareTo(other.expiryPeriod)
        }
    }
}

public inline fun SocialRequestExpiryPeriod.isExpiredWhen(localDateTime: LocalDateTime) = when (this) {
    is SocialRequestExpiryPeriod.NonExpiring -> false;
    is SocialRequestExpiryPeriod.TemporaryUnexpired -> this.expiryPeriod.isBefore(localDateTime);
}

public inline fun SocialRequestExpiryPeriod.isUnexpiredWhen(localDateTime: LocalDateTime) = !this.isExpiredWhen(localDateTime)
