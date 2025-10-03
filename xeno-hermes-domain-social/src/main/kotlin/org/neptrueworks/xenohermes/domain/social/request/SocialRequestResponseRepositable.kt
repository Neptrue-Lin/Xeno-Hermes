package org.neptrueworks.xenohermes.domain.social.request

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester

public interface SocialRequestResponseRepositable : AggregateRepositable<SocialRequestResponseAggregateRoot> {
    public fun fetchByIdentifier(socialRequestId: SocialRequestIdentifier): SocialRequestResponseAggregateRoot
    public fun fetchPrevious(requester: SocialRequestRequester, agent: SocialRequestAgent): SocialRequestResponseAggregateRoot
}