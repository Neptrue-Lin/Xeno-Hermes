package org.neptrueworks.xenohermes.domain.social.request

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.social.request.params.*


public interface SocialRequestAggregatable : Aggregatable {
    val requestId: SocialRequestIdentifier
    val requester: SocialRequestRequester
    val agent: SocialRequestAgent
    val revocationStatus: SocialRequestRevocationStatus
    val revocationPrivilege: SocialRequestRevocationPrivilege
    val responseStatus: SocialResponseStatus
    val responsePrivilege: SocialResponsePrivilege
    val sendDateTime: SocialRequestSendDateTime
    val expiryPeriod: SocialRequestExpiryPeriod
}