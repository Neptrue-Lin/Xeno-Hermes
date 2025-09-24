package org.neptrueworks.xenohermes.domain.interlocution.moderation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.moderation.commands.BanParticipantCommand
import org.neptrueworks.xenohermes.domain.interlocution.moderation.commands.UnbanParticipantCommand
import org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions.InterlocutionBanNotBannedException
import org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions.ParticipantAlreadyBannedException
import org.neptrueworks.xenohermes.domain.interlocution.moderation.exceptions.ParticipantNotBannedException
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.*

public abstract class InterlocutionModerationAggregateRoot : AggregateRoot(), InterlocutionModerationAggregatable {
    public abstract override val moderationAgent: InterlocutionModerationAgent
//    public abstract override val moderator: InterlocutionModerator
    public abstract override val interlocutionBans: InterlocutionBanManifest
    public abstract override var contentRestriction: InterlocutionContentRestriction protected set
    public abstract override var behaviorRestriction: InterlocutionBehaviorRestriction protected set
//    public abstract override var banPrivilege: InterlocutionBanPrivilege protected set

    internal final fun banParticipant(command: BanParticipantCommand) {
//        if (this.banPrivilege.isForbidden())
//            throw InterlocutionBanForbiddenException(command.moderationAgent, command.moderator);
        if (this.interlocutionBans[command.participant].isBanned())
            throw ParticipantAlreadyBannedException(command.moderationAgent, command.moderator, command.participant);
        if (command.ban.isNotBanned())
            throw InterlocutionBanNotBannedException(command.moderationAgent, command.moderator, command.participant);
        
        this.interlocutionBans.ban(command.participant, command.ban);
    }
    
    internal final fun unbanParticipant(command: UnbanParticipantCommand) {
//        if (this.banPrivilege.isForbidden())
//            throw InterlocutionBanForbiddenException(command.moderationAgent, command.moderator);
        if (this.interlocutionBans[command.participant].isNotBanned())
            throw ParticipantNotBannedException(command.moderationAgent, command.moderator, command.participant);
        
        this.interlocutionBans.unban(command.participant);
    }
}
