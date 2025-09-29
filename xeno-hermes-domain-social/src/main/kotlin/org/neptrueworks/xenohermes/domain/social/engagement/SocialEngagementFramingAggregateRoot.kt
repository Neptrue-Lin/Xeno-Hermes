package org.neptrueworks.xenohermes.domain.social.engagement

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.commands.*
import org.neptrueworks.xenohermes.domain.social.engagement.exceptions.*
import org.neptrueworks.xenohermes.domain.social.engagement.params.*

public abstract class SocialEngagementFramingAggregateRoot : AggregateRoot(), SocialEngagementAggregatable {
    public abstract override val engager: SocialEngagementEngager
    public abstract override val engagementThreshold: SocialEngagementThreshold
    public abstract override val requestEngagementPrivilege: SocialRequestEngagementPrivilege
    public abstract override val invitationEngagementPrivilege: SocialInvitationEngagementPrivilege
}