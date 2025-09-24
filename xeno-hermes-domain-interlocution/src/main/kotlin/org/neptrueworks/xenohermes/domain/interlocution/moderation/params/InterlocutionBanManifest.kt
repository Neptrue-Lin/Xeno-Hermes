package org.neptrueworks.xenohermes.domain.interlocution.moderation.params

public abstract class InterlocutionBanManifest {
    public abstract operator fun get(participant: InterlocutionParticipant): InterlocutionBan;
    public abstract fun ban(participant: InterlocutionParticipant, ban: InterlocutionBan);
    public abstract fun unban(participant: InterlocutionParticipant);
}