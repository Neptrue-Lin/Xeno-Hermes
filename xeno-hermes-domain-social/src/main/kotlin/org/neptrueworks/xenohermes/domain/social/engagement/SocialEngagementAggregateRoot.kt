package org.neptrueworks.xenohermes.domain.social.engagement

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.commands.*
import org.neptrueworks.xenohermes.domain.social.engagement.exceptions.*
import org.neptrueworks.xenohermes.domain.social.engagement.params.*

public abstract class SocialEngagementAggregateRoot : AggregateRoot(), SocialEngagementAggregatable {
    public abstract override val engager: SocialEngagementEngager
    public abstract override val engagementManifest: SocialEngagementManifest;
    public abstract override var requestEngagementPrivilege: SocialRequestEngagementPrivilege protected set
    public abstract override var invitationEngagementPrivilege: SocialInvitationEngagementPrivilege protected set

    public abstract override val engagerEngagementThreshold: SocialEngagementThreshold;
    public abstract override val engageeEngagementThreshold: SocialEngagementThreshold;
    
    internal final fun engageInterlocutor(command: EngageInterlocutorCommand) {
        if (this.engagementManifest.isEngaged(command.engagee))
            throw EngageeAlreadyEngagedException(command.engager, command.engagee);
        if (this.engagerEngagementThreshold.isMaximumEngagementExceeds(this.engagementManifest.engagerEngagementCount))
            throw EngagerEngagementThresholdExceededException(command.engager, this.engagerEngagementThreshold);
        if (this.engageeEngagementThreshold.isMaximumEngagementExceeds(this.engagementManifest.engageeEngagementCount))
            throw EngageeEngagementThresholdExceededException(command.engagee, this.engageeEngagementThreshold);
        
        this.engagementManifest.engage(command.engager, command.engagee);
    }
    
    internal final fun disengageInterlocutor(command: DisengageInterlocutorCommand) {
        if (this.engagementManifest.isNotEngaged(command.disengagee))
            throw DisengageeNotEngagedException(command.disengager);
        
        this.engagementManifest.disengage(command.disengager, command.disengagee);
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
}