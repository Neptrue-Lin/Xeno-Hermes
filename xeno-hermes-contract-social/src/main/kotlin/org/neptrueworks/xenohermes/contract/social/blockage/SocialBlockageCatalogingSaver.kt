package org.neptrueworks.xenohermes.contract.social.blockage

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.neptrueworks.xenohermes.contract.social.blockage.params.SocialBlockageCatalogingOperation
import org.springframework.stereotype.Component

@Component
internal final class SocialBlockageCatalogingSaver(
    private val jimmerClient: KSqlClient
) {
    internal final fun save(aggregator: SocialBlockageCatalogingAggregator) {
        this.jimmerClient.saveCommand(aggregator.__resolve() as SocialBlocker, SaveMode.UPDATE_ONLY).execute();
        when (val op = aggregator.blockageCataloging.operation) {
            is SocialBlockageCatalogingOperation.Blocking -> this.jimmerClient.getAssociations(SocialBlocker::blocked)
                .save(op.blocker, op.blockee);
            is SocialBlockageCatalogingOperation.Unblocking -> this.jimmerClient.getAssociations(SocialBlocker::blocked)
                .delete(op.unblocker, op.unblockee);
            else -> {}
        }
    }
}