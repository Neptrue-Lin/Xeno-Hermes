package org.neptrueworks.xenohermes.domain.social.engagement

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.social.engagement.params.*

public interface SocialEngagementAggregatable : Aggregatable {
    val engager: SocialEngagementEngager
    val engagementThreshold: SocialEngagementThreshold
    val requestEngagementPrivilege: SocialRequestEngagementPrivilege
    val invitationEngagementPrivilege: SocialInvitationEngagementPrivilege
}