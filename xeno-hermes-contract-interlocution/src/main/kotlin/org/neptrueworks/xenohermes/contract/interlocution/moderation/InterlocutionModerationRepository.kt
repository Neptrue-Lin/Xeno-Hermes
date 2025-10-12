package org.neptrueworks.xenohermes.contract.interlocution.moderation

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.neptrueworks.xenohermes.contract.interlocution.moderation.params.InterlocutionBanCataloger
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationAggregateRoot
import org.neptrueworks.xenohermes.domain.interlocution.moderation.InterlocutionModerationRepositable
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBan
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerationAgent
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionParticipant
import org.springframework.stereotype.Repository

@Repository
internal final class InterlocutionModerationRepository(
    private val jimmerClient: KSqlClient,
    private val saver: InterlocutionModerationSaver
) : InterlocutionModerationRepositable {
    override fun fetchByIdentifier(moderationAgent: InterlocutionModerationAgent, participant: InterlocutionParticipant): InterlocutionModerationAggregateRoot {
        val moderation = this.jimmerClient.findById(InterlocutionModeration::class, moderationAgent);
        val ban = this.jimmerClient.createQuery(InterlocutionBanning::class) {
            where(table.agent eq moderationAgent)
            where(table.participant eq participant)
            select(table)
        }.fetchOneOrNull();
        
        assert(moderation != null);
        return InterlocutionModerationAggregator(InterlocutionBanCataloger(ban), moderation!!);
    }

    override fun reposit(aggregateRoot: InterlocutionModerationAggregateRoot) {
        this.saver.save(aggregateRoot as InterlocutionModerationAggregator);
    }
}