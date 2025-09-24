package org.neptrueworks.xenohermes.domain.social.request

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRepositable
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester

public interface SocialRequestRepositable : AggregateRepositable<SocialRequestAggregateRoot> {
    public fun fetchByIdentifier(socialRequestId: SocialRequestIdentifier): SocialRequestAggregateRoot
    public fun fetchPrevious(requester: SocialRequestRequester, agent: SocialRequestAgent): SocialRequestAggregateRoot
}