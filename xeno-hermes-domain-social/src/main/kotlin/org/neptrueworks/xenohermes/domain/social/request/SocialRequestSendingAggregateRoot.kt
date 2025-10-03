package org.neptrueworks.xenohermes.domain.social.request

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestExpiryPeriod
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevocationPrivilege
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevocationStatus
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestSendDateTime
import org.neptrueworks.xenohermes.domain.social.request.params.SocialResponsePrivilege
import org.neptrueworks.xenohermes.domain.social.request.params.SocialResponseStatus

public abstract class SocialRequestSendingAggregateRoot : AggregateRoot(), SocialRequestAggregatable {
    public abstract override val requestId: SocialRequestIdentifier
    public abstract override val requester: SocialRequestRequester
    public abstract override val agent: SocialRequestAgent
    public abstract override val revocationStatus: SocialRequestRevocationStatus
    public abstract override val revocationPrivilege: SocialRequestRevocationPrivilege
    public abstract override val responseStatus: SocialResponseStatus
    public abstract override val responsePrivilege: SocialResponsePrivilege
    public abstract override val sendDateTime: SocialRequestSendDateTime
    public abstract override val expiryPeriod: SocialRequestExpiryPeriod
}