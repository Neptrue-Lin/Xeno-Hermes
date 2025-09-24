package org.neptrueworks.xenohermes.domain.common.aggregation

public interface AggregateIdentifierGeneratable<TAggregateIdentifier : AggregateIdentifier> {
    public fun nextIdentifier(): TAggregateIdentifier
}
