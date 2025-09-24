package org.neptrueworks.xenohermes.domain.common.event

import org.neptrueworks.xenohermes.domain.common.models.DomainEntity

/**
 * A domain event marks a detectable significant occurrence which **has caused** state changes in the domain,
 * and the state changes **might trigger** further actions in the domain as well as out of domain.
 * 
 * * Domain events should be **immutable**, indicating the occurrence of event is an established and unchangeable fact.
 * `data class` and `val` property are recommended and common practice for domain events.
 * 
 * * Domain events should be raised close to state changes as much as possible,
 * and could not be raised outside the aggregate.
 * `internal constructor` is recommended to restrict the creation of domain events outside the aggregate.
 * @see DomainEventHandler
 * @see DomainEventRaiseable
 */
public interface DomainEvent : DomainEntity
