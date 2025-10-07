package org.neptrueworks.xenohermes.contract.interlocution.moderation

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.KeyUniqueConstraint
import org.babyfish.jimmer.sql.ManyToOne
import org.neptrueworks.xenohermes.contract.interlocution.moderation.params.InterlocutionBanIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBanActivePeriod
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerator
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant

@Entity
@KeyUniqueConstraint
public interface InterlocutionBanning {
    @Id
    val banId: InterlocutionBanIdentifier
    @Key
    val agent: InterlocutionModerationAgent
    @Key
    val participant: InterlocutionParticipant
    val bannedBy: InterlocutionModerator 
    val activePeriod: InterlocutionBanActivePeriod
    @ManyToOne
    val moderation: InterlocutionModeration
}