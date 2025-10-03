package org.neptrueworks.xenohermes.contract.social.request

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestAggregatable
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.params.*

internal typealias SocialRequestingDraft = SocialRequestDraft.`$`.DraftImpl

@Entity
public interface SocialRequest: SocialRequestAggregatable {
    @Id
    override val requestId: SocialRequestIdentifier
    override val requester: SocialRequestRequester
    override val agent: SocialRequestAgent
    override val revocationStatus: SocialRequestRevocationStatus
    override val revocationPrivilege: SocialRequestRevocationPrivilege
    override val responseStatus: SocialResponseStatus
    override val responsePrivilege: SocialResponsePrivilege
    override val expiryPeriod: SocialRequestExpiryPeriod
    override val sendDateTime: SocialRequestSendDateTime
}

