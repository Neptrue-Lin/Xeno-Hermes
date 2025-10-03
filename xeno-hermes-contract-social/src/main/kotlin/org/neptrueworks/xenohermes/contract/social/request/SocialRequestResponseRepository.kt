package org.neptrueworks.xenohermes.contract.social.request

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestAggregatable
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestResponseAggregateRoot
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestResponseRepositable
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.springframework.stereotype.Repository

@Repository
internal final class SocialRequestResponseRepository(
    private val kSqlClient: KSqlClient
) : SocialRequestResponseRepositable {
    override fun fetchByIdentifier(socialRequestId: SocialRequestIdentifier): SocialRequestResponseAggregateRoot {
        return this.kSqlClient.findById(SocialRequest::class, socialRequestId)
            .run(::SocialRequestResponseAggregator);
    }

    override fun fetchPrevious(requester: SocialRequestRequester, agent: SocialRequestAgent): SocialRequestAggregatable {
        return this.kSqlClient.createQuery(SocialRequest::class) {
            where(table.requester eq requester)
            where(table.agent eq agent)
            orderBy(table.sendDateTime.desc())
            select(table)
        }.fetchFirst().run(::SocialRequestResponseAggregator)
    }

    override fun reposit(aggregateRoot: SocialRequestResponseAggregateRoot) {
        val aggregator = aggregateRoot as SocialRequestResponseAggregator;
        this.kSqlClient.saveCommand(aggregator.__resolve() as SocialRequest, SaveMode.UPDATE_ONLY).execute();
    }
}