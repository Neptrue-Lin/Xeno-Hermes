package org.neptrueworks.xenohermes.domain.interlocution.moderation.params

public sealed interface InterlocutionBanCatalogable{
    public abstract operator fun get(participant: InterlocutionParticipant): InterlocutionBan;
}

public abstract class InterlocutionBanCatalog: InterlocutionBanCatalogable {
    public abstract fun ban(participant: InterlocutionParticipant, activePeriod: InterlocutionBanActivePeriod);
    public abstract fun unban(participant: InterlocutionParticipant);
}