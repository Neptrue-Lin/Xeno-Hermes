package org.neptrueworks.xenohermes.contract.social.engagement

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.neptrueworks.xenohermes.contract.social.engagement.params.SocialEngagementCatalogingOperation

internal final class SocialEngagementCatalogingSaver(
    private val kSqlClient: KSqlClient
) {
    internal final fun save(aggregator: SocialEngagementCatalogingAggregator) {
        this.kSqlClient.saveCommand(aggregator.__resolve() as SocialEngager, SaveMode.UPDATE_ONLY).execute();
        when (val op = aggregator.engagementCataloging.operation) {
            is SocialEngagementCatalogingOperation.Engaging -> {
                this.kSqlClient.getAssociations(SocialEngager::engaged).save(op.engager, op.engagee);
                this.kSqlClient.getAssociations(SocialEngager::engaged).save(op.engagee, op.engager);
            } 
            is SocialEngagementCatalogingOperation.Disengaging -> {
                this.kSqlClient.getAssociations(SocialEngager::engaged).delete(op.engager, op.engagee);
                this.kSqlClient.getAssociations(SocialEngager::engaged).delete(op.engagee, op.engager);
            }
            else -> {}
        }
    }
}