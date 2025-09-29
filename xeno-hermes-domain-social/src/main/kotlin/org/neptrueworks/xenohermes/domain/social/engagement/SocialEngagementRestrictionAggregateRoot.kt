package org.neptrueworks.xenohermes.domain.social.engagement

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.commands.*
import org.neptrueworks.xenohermes.domain.social.engagement.exceptions.*
import org.neptrueworks.xenohermes.domain.social.engagement.params.*

public abstract class SocialEngagementRestrictionAggregateRoot : AggregateRoot(), SocialEngagementAggregatable {
    public abstract override val engager: SocialEngagementEngager
    public abstract override val engagementThreshold: SocialEngagementThreshold
    public abstract override var requestEngagementPrivilege: SocialRequestEngagementPrivilege protected set
    public abstract override var invitationEngagementPrivilege: SocialInvitationEngagementPrivilege protected set

    internal final fun permitRequestEngagement(command: PermitRequestEngagementCommand) {
        if (this.requestEngagementPrivilege.isPermitted())
            throw RequestEngagementAlreadyPermittedException(command.engager);

        this.requestEngagementPrivilege = SocialRequestEngagementPrivilege.PERMITTED;
    }

    internal final fun forbidRequestEngagement(command: ForbidRequestEngagementCommand) {
        if (this.requestEngagementPrivilege.isForbidden())
            throw RequestEngagementAlreadyForbiddenException(command.engager);

        this.requestEngagementPrivilege = SocialRequestEngagementPrivilege.FORBIDDEN;
    }

    internal final fun permitInvitationEngagement(command: PermitInvitationEngagementCommand) {
        if (this.invitationEngagementPrivilege.isPermitted())
            throw InvitationEngagementAlreadyPermittedException(command.engager);

        this.invitationEngagementPrivilege = SocialInvitationEngagementPrivilege.PERMITTED;
    }

    internal final fun forbidInvitationEngagement(command: ForbidInvitationEngagementCommand) {
        if (this.invitationEngagementPrivilege.isForbidden())
            throw InvitationEngagementAlreadyForbiddenException(command.engager);

        this.invitationEngagementPrivilege = SocialInvitationEngagementPrivilege.FORBIDDEN;
    }
}