package org.neptrueworks.xenohermes.domain.social.engagement

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.social.engagement.params.*

public interface SocialEngagementAggregatable : Aggregatable {
    val engager: SocialEngagementEngager
    val engagementManifest: SocialEngagementManifest
    val requestEngagementPrivilege: SocialRequestEngagementPrivilege
    val invitationEngagementPrivilege: SocialInvitationEngagementPrivilege
    val engagerEngagementThreshold: SocialEngagementThreshold
    val engageeEngagementThreshold: SocialEngagementThreshold
}