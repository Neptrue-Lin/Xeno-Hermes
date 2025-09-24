package org.neptrueworks.xenohermes.domain.interlocution.moderation.params

import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBan.*

public sealed class InterlocutionBan {
    internal data object Unbanned : InterlocutionBan()
    public data object PermanentBanned : InterlocutionBan()
    public data class TemporalBanned(val banUntil: InterlocutionBanTemporal) : InterlocutionBan()
}

public inline fun InterlocutionBan.isBanned() = when (this) {
    is Unbanned -> false;
    is PermanentBanned -> true;
    is TemporalBanned -> this.banUntil.isBanned();
}

public inline fun InterlocutionBan.isNotBanned() = !this.isBanned();