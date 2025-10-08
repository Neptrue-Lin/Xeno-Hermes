package org.neptrueworks.xenohermes.contract.social.blockage.mapping

import org.babyfish.jimmer.sql.association.Association
import org.babyfish.jimmer.sql.ast.tuple.Tuple2
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.KTransientResolver
import org.babyfish.jimmer.sql.kt.ast.expression.count
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.babyfish.jimmer.sql.kt.ast.table.KNonNullTable
import org.babyfish.jimmer.sql.kt.ast.table.source
import org.neptrueworks.xenohermes.contract.social.blockage.SocialBlocker
import org.neptrueworks.xenohermes.contract.social.blockage.blocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCount
import org.springframework.stereotype.Component

@Component
public final class SocialBlockageCountMapper(
    private val kSqlClient: KSqlClient
) : KTransientResolver<SocialBlockageBlocker, SocialBlockageCount> {
    override fun resolve(ids: Collection<SocialBlockageBlocker>): Map<SocialBlockageBlocker, SocialBlockageCount> {
        return this.kSqlClient.queries.forList(SocialBlocker::blocked) {
            where(table.blockerId valueIn ids)
            groupBy(table.blockerId)
            select(table.blockerId, count(table.blockerId))
        }.execute().associateBy({ it.blocker }) { it.blockageCount }
    }
    
    private final inline val Blockage.blockerId get() = this.source.blocker;
    private final inline val Select.blocker get() = this._1;
    private final inline val Select.blockageCount get() = SocialBlockageCount(this._2);

    private typealias Blockage = KNonNullTable<Association<SocialBlocker, SocialBlocker>>;
    private typealias Select = Tuple2<SocialBlockageBlocker, Long>;
}