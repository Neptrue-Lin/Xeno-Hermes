package org.neptrueworks.xenohermes.domain.social.engagement

import org.neptrueworks.xenohermes.domain.common.models.DomainService
import org.neptrueworks.xenohermes.domain.social.engagement.commands.FrameSocialEngagementCommand
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementThreshold
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialInvitationEngagementPrivilege
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialRequestEngagementPrivilege

public abstract class SocialEngagementFactory : DomainService {
    internal final fun frameActor(command: FrameSocialEngagementCommand): SocialEngagementAggregateRoot {
        return produceSocialEngagement(
            engager = command.engager,
            requestEngagementPrivilege = command.requestEngagementPrivilege,
            invitationEngagementPrivilege = command.invitationEngagementPrivilege,
            engagerEngagementThreshold = command.engagerEngagementThreshold,
        )
    }
    
    protected abstract fun produceSocialEngagement(
        engager: SocialEngagementEngager,
        requestEngagementPrivilege: SocialRequestEngagementPrivilege,
        invitationEngagementPrivilege: SocialInvitationEngagementPrivilege,
        engagerEngagementThreshold: SocialEngagementThreshold,
    ): SocialEngagementAggregateRoot;
}