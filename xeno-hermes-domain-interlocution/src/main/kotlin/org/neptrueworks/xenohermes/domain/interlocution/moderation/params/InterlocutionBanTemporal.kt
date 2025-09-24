package org.neptrueworks.xenohermes.domain.interlocution.moderation.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass
import java.time.LocalDateTime

@InlineClass
public data class InterlocutionBanTemporal(val temporal: LocalDateTime) : Comparable<InterlocutionBanTemporal> {
    public override fun compareTo(other: InterlocutionBanTemporal) = 
        this.temporal.compareTo(other.temporal);
}

public inline fun InterlocutionBanTemporal.isBanned() =
    this.temporal.isAfter(LocalDateTime.now());
public inline fun InterlocutionBanTemporal.isNotBanned() =
    this.temporal.isBefore(LocalDateTime.now());
