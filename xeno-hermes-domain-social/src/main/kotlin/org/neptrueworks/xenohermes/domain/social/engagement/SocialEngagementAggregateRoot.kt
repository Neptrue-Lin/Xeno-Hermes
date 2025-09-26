package org.neptrueworks.xenohermes.domain.social.engagement

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.commands.*
import org.neptrueworks.xenohermes.domain.social.engagement.exceptions.*
import org.neptrueworks.xenohermes.domain.social.engagement.params.*

public abstract class SocialEngagementAggregateRoot : AggregateRoot(), SocialEngagementAggregatable {
    public abstract override val engager: SocialEngagementEngager
    public abstract override val engagementThreshold: SocialEngagementThreshold
    public abstract override var requestEngagementPrivilege: SocialRequestEngagementPrivilege protected set
    public abstract override var invitationEngagementPrivilege: SocialInvitationEngagementPrivilege protected set
    protected abstract val engagementCatalog: SocialEngagementCatalog
    
    public final fun checkEngagement(engagee: SocialEngagementEngagee) =
        this.engagementCatalog.checkEngagement(engagee)

    internal final fun engageInterlocutor(command: EngageInterlocutorCommand) {
        val engagement = this.engagementCatalog.checkEngagement(command.engagee);
        if (engagement.isEngaged()) 
            throw EngageeAlreadyEngagedException(command.engager, command.engagee);
        
        engagement as SocialEngagement.NotEngaged;
        if (engagement.isEngagerMaximumEngagementExceeds())
            throw EngagerEngagementThresholdExceededException(command.engager, this.engagementThreshold);
        if (engagement.isEngageeMaximumEngagementExceeds())
            throw EngageeEngagementThresholdExceededException(command.engagee, engagement.engageeEngagementThreshold);

        this.engagementCatalog.engage(command.engager, command.engagee);
    }

    internal final fun disengageInterlocutor(command: DisengageInterlocutorCommand) {
        val engagement = this.engagementCatalog.checkEngagement(command.disengagee);
        if (engagement.isNotEngaged())
            throw DisengageeNotEngagedException(command.disengager);

        this.engagementCatalog.disengage(command.disengager, command.disengagee);
    }

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
    
    private final inline fun SocialEngagement.NotEngaged.isEngagerMaximumEngagementExceeds() =
        engagementThreshold.isMaximumEngagementExceeds(this.engagerEngagementCount)
    private final inline fun SocialEngagement.NotEngaged.isEngageeMaximumEngagementExceeds() =
        this.engageeEngagementThreshold.isMaximumEngagementExceeds(this.engageeEngagementCount)
}