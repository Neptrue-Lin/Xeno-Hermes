package org.neptrueworks.xenohermes.contract.social.request

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.neptrueworks.xenohermes.contract.social.request.SocialRequestDraft.`$`.produce
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestResponseAggregateRoot
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestSendingFactory
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifierGenerator
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestSendingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestExpiryPeriod
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevocationPrivilege
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevocationStatus
import org.neptrueworks.xenohermes.domain.social.request.params.SocialResponsePrivilege
import org.neptrueworks.xenohermes.domain.social.request.params.SocialResponseStatus
import org.springframework.stereotype.Component

@Component
internal final class SocialRequestSendingProducer(
    protected override val blockageCatalogRepository: SocialBlockageCatalogingRepositable,
    protected override val engagementCatalogRepository: SocialEngagementCatalogingRepositable,
    protected override val identifierGenerator: SocialRequestIdentifierGenerator,
) : SocialRequestSendingFactory() {
    protected override fun produceSocialRequest(
        requestId: SocialRequestIdentifier,
        requester: SocialRequestRequester,
        agent: SocialRequestAgent,
        revocationStatus: SocialRequestRevocationStatus,
        revocationPrivilege: SocialRequestRevocationPrivilege,
        responseStatus: SocialResponseStatus,
        responsePrivilege: SocialResponsePrivilege,
        expiryPeriod: SocialRequestExpiryPeriod,
    ): SocialRequestSendingAggregateRoot = SocialRequestSendingAggregator(produce {
        this.requestId = requestId;
        this.requester = requester;
        this.agent = agent;
        this.revocationStatus = revocationStatus;
        this.revocationPrivilege = revocationPrivilege;
        this.responseStatus = responseStatus;
        this.responsePrivilege = responsePrivilege;
        this.expiryPeriod = expiryPeriod;
    })
}