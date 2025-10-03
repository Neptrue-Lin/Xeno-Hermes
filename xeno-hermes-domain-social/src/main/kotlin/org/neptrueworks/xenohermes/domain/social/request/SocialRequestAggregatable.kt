package org.neptrueworks.xenohermes.domain.social.request

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.social.request.params.*
import java.time.LocalDateTime


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

public inline fun SocialRequestAggregatable.isPendingWhen(currentDateTime: LocalDateTime) =
    this.expiryPeriod.isUnexpiredWhen(currentDateTime)
            && this.revocationStatus.isEnduring()
            && this.responseStatus.isNotResponded()

public inline fun SocialRequestAggregatable.isNotPendingWhen(currentDateTime: LocalDateTime) =
    this.expiryPeriod.isExpiredWhen(currentDateTime)
            || this.revocationStatus.isRevoked()
            || this.responseStatus.isResponded()
