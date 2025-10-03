package org.neptrueworks.xenohermes.contract.social.engagement

import org.babyfish.jimmer.Formula
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.JoinTable
import org.babyfish.jimmer.sql.ManyToMany
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementAggregatable
import org.neptrueworks.xenohermes.domain.social.engagement.params.*

internal typealias SocialEngagementDraft = SocialEngagerDraft.`$`.DraftImpl;

@Entity
public interface SocialEngager : SocialEngagementAggregatable {
    @Id
    override val engager: SocialEngagementEngager
    override val engagementThreshold: SocialEngagementThreshold
    override val requestEngagementPrivilege: SocialRequestEngagementPrivilege
    override val invitationEngagementPrivilege: SocialInvitationEngagementPrivilege

    @Formula(dependencies = ["engaged"])
    val engagementCount get() = SocialEngagementCount(this.engaged.size)

    @ManyToMany
    @JoinTable(
        name = "social_engagement",
        joinColumnName = "engager",
        inverseJoinColumnName = "engagee"
    )
    val engaged: List<SocialEngager>
}