package org.neptrueworks.xenohermes.domain.common.aggregation

import org.neptrueworks.xenohermes.domain.common.models.DomainService

interface AggregateRepositable<TAggregateRoot : Aggregatable> : DomainService {
    public fun reposit(aggregateRoot: TAggregateRoot)
}
