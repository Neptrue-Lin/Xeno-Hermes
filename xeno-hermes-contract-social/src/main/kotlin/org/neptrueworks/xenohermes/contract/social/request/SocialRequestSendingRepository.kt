package org.neptrueworks.xenohermes.contract.social.request

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestAggregatable
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestResponseAggregateRoot
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestResponseRepositable
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestSendingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestSendingRepositable
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.springframework.stereotype.Repository

@Repository
internal final class SocialRequestSendingRepository(
    private val kSqlClient: KSqlClient
) : SocialRequestSendingRepositable {
    override fun reposit(aggregateRoot: SocialRequestSendingAggregateRoot) {
        val aggregator = aggregateRoot as SocialRequestSendingAggregator;
        this.kSqlClient.saveCommand(aggregator.resolve(), SaveMode.INSERT_ONLY).execute();
    }
}