package org.neptrueworks.xenohermes.domain.interlocution.moderation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.moderation.commands.BanParticipantCommand
import org.neptrueworks.xenohermes.domain.interlocution.moderation.commands.UnbanParticipantCommand
import org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions.InterlocutionBanInactiveException
import org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions.ParticipantAlreadyBannedException
import org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions.ParticipantNotBannedException
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.*

public abstract class InterlocutionModerationAggregateRoot : AggregateRoot(), InterlocutionModerationAggregatable {
    public abstract override val moderationAgent: InterlocutionModerationAgent
    public abstract override var contentRestriction: InterlocutionContentRestriction protected set
    public abstract override var behaviorRestriction: InterlocutionBehaviorRestriction protected set
    protected abstract val banCataloging: InterlocutionBanCatalog
    public final val banCatalog: InterlocutionBanCatalogable = this.banCataloging

    internal final fun banParticipant(command: BanParticipantCommand) {
        if (this.banCatalog[command.participant].isBanned())
            throw ParticipantAlreadyBannedException(command.moderationAgent, command.moderator, command.participant);
        if (command.activePeriod.isInactive())
            throw InterlocutionBanInactiveException(command.moderationAgent, command.moderator, command.participant);
        
        this.banCataloging.ban(command.participant, command.activePeriod);
    }
    
    internal final fun unbanParticipant(command: UnbanParticipantCommand) {
        if (this.banCatalog[command.participant].isNotBanned())
            throw ParticipantNotBannedException(command.moderationAgent, command.moderator, command.participant);
        
        this.banCataloging.unban(command.participant);
    }
}
