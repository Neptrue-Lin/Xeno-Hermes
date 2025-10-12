package org.neptrueworks.xenohermes.contract.social.blockage

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageEstablishingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageEstablishingRepositable
import org.springframework.stereotype.Repository

@Repository
internal final class SocialBlockageEstablishingRepository(
    private val jimmerClient: KSqlClient,
) : SocialBlockageEstablishingRepositable{
    override fun reposit(aggregateRoot: SocialBlockageEstablishingAggregateRoot) {
        val aggregator = aggregateRoot as SocialBlockageEstablishingAggregator;
        this.jimmerClient.saveCommand(aggregator.resolve(), SaveMode.UPDATE_ONLY).execute();
    }
}