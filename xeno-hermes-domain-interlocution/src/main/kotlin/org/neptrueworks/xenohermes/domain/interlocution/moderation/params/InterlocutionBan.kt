package org.neptrueworks.xenohermes.domain.interlocution.moderation.params

import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBan.*

public sealed class InterlocutionBan {
    public data object NotBanned: InterlocutionBan()
    public data class Banned(val activePeriod: InterlocutionBanActivePeriod) : InterlocutionBan()
}

public inline fun InterlocutionBan.isBanned() = this is InterlocutionBan.Banned;
public inline fun InterlocutionBan.isNotBanned() = this is InterlocutionBan.NotBanned;