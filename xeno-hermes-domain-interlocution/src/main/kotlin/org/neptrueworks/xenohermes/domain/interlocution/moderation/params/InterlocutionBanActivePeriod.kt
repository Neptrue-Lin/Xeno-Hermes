package org.neptrueworks.xenohermes.domain.interlocution.moderation.params

import java.time.LocalDateTime

public sealed class InterlocutionBanActivePeriod {
    public data object Permanent: InterlocutionBanActivePeriod();
    public data class Temporal(val banUntil: LocalDateTime) : InterlocutionBanActivePeriod();
}

public inline fun InterlocutionBanActivePeriod.isActive() = when (this) {
    is InterlocutionBanActivePeriod.Permanent -> true;
    is InterlocutionBanActivePeriod.Temporal -> this.banUntil.isAfter(LocalDateTime.now());
}
public inline fun InterlocutionBanActivePeriod.isInactive() = !this.isActive();
