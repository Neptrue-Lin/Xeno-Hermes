package org.neptrueworks.xenohermes.contract.interlocution.moderation

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.neptrueworks.xenohermes.contract.interlocution.moderation.params.InterlocutionBanCatalogingOperation
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionModerator

internal final class InterlocutionModerationSaver(
    private val kSqlClient: KSqlClient,
) {
    internal final fun save(aggregator: InterlocutionModerationAggregator) {
        when (val op = aggregator.banCataloging.operation) {
            is InterlocutionBanCatalogingOperation.Banning -> aggregator.banned().addBy {
                this.agent = aggregator.moderationAgent;
                this.participant = op.participant;
                this.activePeriod = op.activePeriod;
            }
            is InterlocutionBanCatalogingOperation.Unbanning -> this.kSqlClient.createDelete(InterlocutionBanning::class) {
                where(table.agent eq aggregator.moderationAgent)
                where(table.participant eq op.participant)
            }.execute();
            else -> {}
        }
        this.kSqlClient.saveCommand(aggregator.__resolve() as InterlocutionModeration).execute();
    }
}