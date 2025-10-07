package org.neptrueworks.xenohermes.contract.interlocution.moderation.params

import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBanActivePeriod
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant

internal sealed class InterlocutionBanCatalogingOperation {
    internal data object CheckingNotBanned: InterlocutionBanCatalogingOperation();
    internal data class CheckingBanned(val activePeriod: InterlocutionBanActivePeriod): InterlocutionBanCatalogingOperation();
    internal data class Banning(val participant: InterlocutionParticipant, val activePeriod: InterlocutionBanActivePeriod): InterlocutionBanCatalogingOperation();
    internal data class Unbanning(val participant: InterlocutionParticipant) : InterlocutionBanCatalogingOperation();
}