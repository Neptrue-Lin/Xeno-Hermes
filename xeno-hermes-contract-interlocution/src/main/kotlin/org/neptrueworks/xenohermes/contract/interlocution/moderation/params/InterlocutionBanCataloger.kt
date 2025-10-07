package org.neptrueworks.xenohermes.contract.interlocution.moderation.params

import org.neptrueworks.xenohermes.contract.interlocution.moderation.InterlocutionBanning
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBan
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBanActivePeriod
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBanCatalog
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant

internal final class InterlocutionBanCataloger(
    ban: InterlocutionBanning?,
) : InterlocutionBanCatalog() {
    internal var operation = if (ban == null)
         InterlocutionBanCatalogingOperation.CheckingNotBanned
    else InterlocutionBanCatalogingOperation.CheckingBanned(ban.activePeriod)
        private set;

    override operator fun get(participant: InterlocutionParticipant) = when (val op = this.operation) {
        is InterlocutionBanCatalogingOperation.CheckingNotBanned -> InterlocutionBan.NotBanned
        is InterlocutionBanCatalogingOperation.CheckingBanned -> InterlocutionBan.Banned(op.activePeriod)
        else -> throw IllegalStateException("Invalid to get interlocution ban");
    }

    override fun ban(participant: InterlocutionParticipant, activePeriod: InterlocutionBanActivePeriod) = when (val op = this.operation) {
        is InterlocutionBanCatalogingOperation.CheckingBanned -> {}
        is InterlocutionBanCatalogingOperation.CheckingNotBanned -> 
            this.operation = InterlocutionBanCatalogingOperation.Banning(participant, activePeriod);
        else -> throw IllegalStateException("Invalid to ban interlocution participant");
    }

    override fun unban(participant: InterlocutionParticipant) = when (val op = this.operation) {
        is InterlocutionBanCatalogingOperation.CheckingNotBanned -> {}
        is InterlocutionBanCatalogingOperation.CheckingBanned -> 
            this.operation = InterlocutionBanCatalogingOperation.Unbanning(participant);
        else -> throw IllegalStateException("Invalid to unban interlocution participant");
    }
}