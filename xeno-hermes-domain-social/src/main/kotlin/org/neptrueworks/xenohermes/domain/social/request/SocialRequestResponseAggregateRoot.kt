package org.neptrueworks.xenohermes.domain.social.request

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.request.commands.AcceptSocialRequestCommand
import org.neptrueworks.xenohermes.domain.social.request.commands.RejectSocialRequestCommand
import org.neptrueworks.xenohermes.domain.social.request.commands.RevokeSocialRequestCommand
import org.neptrueworks.xenohermes.domain.social.request.exceptions.*
import org.neptrueworks.xenohermes.domain.social.request.params.*
import java.time.LocalDateTime

public abstract class SocialRequestResponseAggregateRoot : AggregateRoot(), SocialRequestAggregatable {
    public abstract override val requestId: SocialRequestIdentifier
    public abstract override val requester: SocialRequestRequester
    public abstract override val agent: SocialRequestAgent
    public abstract override var revocationStatus: SocialRequestRevocationStatus protected set
    public abstract override val revocationPrivilege: SocialRequestRevocationPrivilege
    public abstract override var responseStatus: SocialResponseStatus protected set
    public abstract override val responsePrivilege: SocialResponsePrivilege
    public abstract override val sendDateTime: SocialRequestSendDateTime
    public abstract override var expiryPeriod: SocialRequestExpiryPeriod protected set
        
    internal final fun revokeSocialRequest(command: RevokeSocialRequestCommand) {
        if (this.revocationPrivilege.isForbidden())
            throw SocialRequestRevocationForbiddenException(command.requestId, command.revoker, this.agent);
        if (this.revocationStatus.isRevoked())
            throw SocialRequestRevokedException(command.requestId, this.requester, this.agent);
        if (this.expiryPeriod.isExpiredWhen(command.revocationDateTime.revokedAt))
            throw SocialRequestExpiredException(command.requestId, this.requester, this.agent, this.expiryPeriod);
        if (this.responseStatus.isResponded())
            throw SocialRequestRespondedException(command.requestId, this.requester, this.agent, this.responseStatus);
        
        this.revocationStatus = SocialRequestRevocationStatus.REVOKED;
    }
    
    internal final fun acceptSocialRequest(command: AcceptSocialRequestCommand) {
        if (this.responsePrivilege.isForbidden())
            throw SocialResponseForbiddenException(command.requestId, command.responder, this.agent);
        if (this.expiryPeriod.isExpiredWhen(command.acceptanceDateTime.acceptedAt))
            throw SocialRequestExpiredException(command.requestId, this.requester, this.agent, this.expiryPeriod);
        if (this.responseStatus.isResponded())
            throw SocialRequestRespondedException(command.requestId, this.requester, this.agent, this.responseStatus);
        
        this.responseStatus = SocialResponseStatus.ACCEPTED;
    }
    
    internal final fun rejectSocialRequest(command: RejectSocialRequestCommand) {
        if (this.responsePrivilege.isForbidden()) 
            throw SocialResponseForbiddenException(command.requestId, command.responder, this.agent);
        if (this.expiryPeriod.isExpiredWhen(command.rejectionDateTime.rejectedAt))
            throw SocialRequestExpiredException(command.requestId, this.requester, this.agent, this.expiryPeriod);
        if (this.responseStatus.isResponded())
            throw SocialRequestRespondedException(command.requestId, this.requester, this.agent, this.responseStatus);
        
        this.responseStatus = SocialResponseStatus.REJECTED;
    }
}