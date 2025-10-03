package org.neptrueworks.xenohermes.domain.social.invitation

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.invitation.commands.AcceptSocialInvitationCommand
import org.neptrueworks.xenohermes.domain.social.invitation.commands.RevokeSocialInvitationCommand
import org.neptrueworks.xenohermes.domain.social.invitation.exceptions.*
import org.neptrueworks.xenohermes.domain.social.invitation.params.*
import java.time.LocalDateTime

public abstract class SocialInvitationResponseAggregateRoot: AggregateRoot(), SocialInvitationAggregatable {
    public abstract override val invitationId: SocialInvitationIdentifier
    public abstract override val issuer: SocialInvitationIssuer
    public abstract override val agent: SocialInvitationAgent
    public abstract override val audience: SocialInvitationAudience
    public abstract override var activePeriod: SocialInvitationActivePeriod protected set
    public abstract override var expiryPeriod: SocialInvitationExpiryPeriod protected set
    public abstract override var revocationStatus: SocialInvitationRevocationStatus protected set
    public abstract override var acceptanceStatus: SocialInvitationAcceptanceStatus protected set
    public abstract override val issueDateTime: SocialInvitationIssueDateTime
//    public abstract val revocationPrivilege: SocialInvitationRevocationPrivilege
//    public abstract val invocationPrivilege: SocialInvitationInvocationPrivilege
        
    internal final fun acceptSocialInvitation(command: AcceptSocialInvitationCommand) {
//        if (this.invocationPrivilege.isForbidden())
//            throw SocialInvitationAcceptanceForbiddenException(command.invitationId, command.accepter, this.agent);
        if (this.expiryPeriod.isExpired(command.acceptanceDateTime.invokedAt))
            throw SocialInvitationExpiredException(command.invitationId, this.agent, this.expiryPeriod);
        if (this.activePeriod.isActivated(command.acceptanceDateTime.invokedAt))
            throw InactiveSocialInvitationException(command.invitationId, command.accepter, this.agent, this.activePeriod);
        
        if (this.revocationStatus.isRevoked())
            throw SocialInvitationRevokedException(command.invitationId, command.accepter, this.agent);
        if (this.acceptanceStatus.isAccepted())
            throw SocialInvitationAcceptedException(command.invitationId, command.accepter, this.agent);
        
        if (this.audience.isNotAudience(command.accepter))
            throw NotInvitationAudienceException(command.invitationId, command.accepter, this.agent);
        
        this.acceptanceStatus = SocialInvitationAcceptanceStatus.ACCEPTED;
    }
    
    internal final fun revokeSocialInvitation(command: RevokeSocialInvitationCommand) {
//        if (this.revocationPrivilege.isForbidden())
//            throw SocialInvitationRevocationForbiddenException(command.invitationId, command.revoker, this.agent);
        if (this.expiryPeriod.isExpired(command.revocationDateTime.revokedAt))
            throw SocialInvitationExpiredException(command.invitationId, this.agent, this.expiryPeriod);
        if (this.revocationStatus.isRevoked())
            throw SocialInvitationAlreadyRevokedException(command.invitationId, command.revoker, this.agent);
        
        this.revocationStatus = SocialInvitationRevocationStatus.REVOKED;
    }
}
