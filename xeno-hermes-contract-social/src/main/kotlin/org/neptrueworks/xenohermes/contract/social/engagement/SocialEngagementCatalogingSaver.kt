package org.neptrueworks.xenohermes.contract.social.engagement

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.neptrueworks.xenohermes.contract.social.engagement.params.SocialEngagementCatalogingOperation

internal final class SocialEngagementCatalogingSaver(
    private val kSqlClient: KSqlClient
) {
    internal final fun save(aggregator: SocialEngagementCatalogingAggregator) {
        this.kSqlClient.saveCommand(aggregator.__resolve() as SocialEngager, SaveMode.UPDATE_ONLY).execute();
        when (val operation = aggregator.engagementCataloging.operation) {
            is SocialEngagementCatalogingOperation.Engaging -> this.kSqlClient.getAssociations(SocialEngager::engaged)
                .save(operation.engager.identifier, operation.engagee.identifier);
            is SocialEngagementCatalogingOperation.Disengaging -> this.kSqlClient.getAssociations(SocialEngager::engaged)
                .delete(operation.engager.identifier, operation.engagee.identifier);
            else -> return;
        }
    }
}