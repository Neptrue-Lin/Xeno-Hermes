package org.neptrueworks.xenohermes.contract.social.blockage

import org.babyfish.jimmer.Formula
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.JoinTable
import org.babyfish.jimmer.sql.ManyToMany
import org.babyfish.jimmer.sql.OneToMany
import org.babyfish.jimmer.sql.Transient
import org.neptrueworks.xenohermes.contract.social.blockage.mapping.SocialBlockageCountMapper
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageAggregatable
import org.neptrueworks.xenohermes.domain.social.blockage.params.*

internal typealias SocialBlockageDraft = SocialBlockerDraft.`$`.DraftImpl;

@Entity
public interface SocialBlocker: SocialBlockageAggregatable {
    @Id
    override val blocker: SocialBlockageBlocker
    override val blockageThreshold: SocialBlockageThreshold

    @Transient(SocialBlockageCountMapper::class)
    val blockageCount: SocialBlockageCount

    @ManyToMany
    @JoinTable(
        name = "social_blockage",
        joinColumnName = "blocker",
        inverseJoinColumnName = "blockee"
    )
    val blocked: List<SocialBlocker>
}