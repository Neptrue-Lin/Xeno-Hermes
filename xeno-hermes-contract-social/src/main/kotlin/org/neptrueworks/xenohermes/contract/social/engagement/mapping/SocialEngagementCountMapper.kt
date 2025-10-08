package org.neptrueworks.xenohermes.contract.social.engagement.mapping

import org.babyfish.jimmer.sql.association.Association
import org.babyfish.jimmer.sql.ast.tuple.Tuple2
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.KTransientResolver
import org.babyfish.jimmer.sql.kt.ast.expression.count
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.babyfish.jimmer.sql.kt.ast.table.KNonNullTable
import org.babyfish.jimmer.sql.kt.ast.table.source
import org.neptrueworks.xenohermes.contract.social.engagement.SocialEngager
import org.neptrueworks.xenohermes.contract.social.engagement.engaged
import org.neptrueworks.xenohermes.contract.social.engagement.engager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementCount
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Component

@Component
public final class SocialEngagementCountMapper(
    private val kSqlClient: KSqlClient,
) : KTransientResolver<SocialEngagementEngager, SocialEngagementCount> {
    override fun resolve(ids: Collection<SocialEngagementEngager>): Map<SocialEngagementEngager, SocialEngagementCount> {
        return this.kSqlClient.queries.forList(SocialEngager::engaged) {
            where(table.engagerId valueIn ids)
            groupBy(table.engagerId)
            select(table.engagerId, count(table.engagerId))
        }.execute().associateBy({ it.engager }) { it.engagementCount }
    }
    
    private final inline val Engagement.engagerId get() = this.source.engager;
    private final inline val Select.engager get() = this._1;
    private final inline val Select.engagementCount get() = SocialEngagementCount(this._2);
    
    private typealias Engagement = KNonNullTable<Association<SocialEngager, SocialEngager>>;
    private typealias Select = Tuple2<SocialEngagementEngager, Long>;
}
