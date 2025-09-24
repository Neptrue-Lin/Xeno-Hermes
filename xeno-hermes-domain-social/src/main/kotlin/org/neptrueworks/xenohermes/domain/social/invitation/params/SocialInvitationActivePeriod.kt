package org.neptrueworks.xenohermes.domain.social.invitation.params

import java.time.LocalDateTime

public sealed class SocialInvitationActivePeriod : Comparable<SocialInvitationActivePeriod> {
    public data object PermanentPassive : SocialInvitationActivePeriod() {
        override fun compareTo(other: SocialInvitationActivePeriod): Int = when (other) {
            is PermanentPassive -> 0
            is TemporaryActive -> -1
            is PermanentActive -> -1
        }
    }
    public data object PermanentActive : SocialInvitationActivePeriod() {
        override fun compareTo(other: SocialInvitationActivePeriod): Int = when (other) {
            is PermanentPassive -> 1
            is TemporaryActive -> 1
            is PermanentActive -> 0
        }
    }
    public data class TemporaryActive(val activatedAt: LocalDateTime) : SocialInvitationActivePeriod() {
        override fun compareTo(other: SocialInvitationActivePeriod): Int = when (other) {
            is PermanentPassive -> 1
            is TemporaryActive -> this.activatedAt.compareTo(other.activatedAt)
            is PermanentActive -> -1
        }
    }
}

public inline fun SocialInvitationActivePeriod.isActivated(currentDateTime: LocalDateTime) = when (this) {
    is SocialInvitationActivePeriod.PermanentPassive -> false;
    is SocialInvitationActivePeriod.PermanentActive -> true;
    is SocialInvitationActivePeriod.TemporaryActive -> this.activatedAt.isBefore(currentDateTime);
}

public inline fun SocialInvitationActivePeriod.isNotActivated(currentDateTime: LocalDateTime) = 
    !this.isActivated(currentDateTime);
