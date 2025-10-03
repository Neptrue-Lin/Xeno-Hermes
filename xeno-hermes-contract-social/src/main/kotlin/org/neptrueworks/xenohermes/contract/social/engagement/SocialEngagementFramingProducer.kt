package org.neptrueworks.xenohermes.contract.social.engagement

import org.neptrueworks.xenohermes.contract.social.engagement.SocialEngagerDraft.`$`.produce
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementFramingFactory
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementFramingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementThreshold
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialInvitationEngagementPrivilege
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialRequestEngagementPrivilege
import org.springframework.stereotype.Component

@Component
internal final class SocialEngagementFramingProducer: SocialEngagementFramingFactory() {
    protected override fun produceSocialEngagement(
        engager: SocialEngagementEngager,
        requestEngagementPrivilege: SocialRequestEngagementPrivilege,
        invitationEngagementPrivilege: SocialInvitationEngagementPrivilege,
        engagerEngagementThreshold: SocialEngagementThreshold,
    ): SocialEngagementFramingAggregateRoot = SocialEngagementFramingAggregator(produce {
        this.engager = engager;
        this.requestEngagementPrivilege = requestEngagementPrivilege;
        this.invitationEngagementPrivilege = invitationEngagementPrivilege;
        this.engagementThreshold = engagerEngagementThreshold;
    })
}