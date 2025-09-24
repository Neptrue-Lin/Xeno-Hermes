package org.neptrueworks.xenohermes.domain.common.event

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler

/**
 * A domain event is an event with domain significance which is detected by the observer,
 * outside the intrinsic model which holds and mutates the states.
 * 
 * Domain events should be initialized and raised by the command handler, 
 * indicating the observer outside the aggregate, rather than the aggregate itself.
 *
 * @param TDomainEvent The type of the domain event to be raised.
 * @see DomainEvent
 * @see CommandHandler
 */
public interface DomainEventRaiseable<in TDomainEvent : DomainEvent> {
    public fun raise(event: TDomainEvent);
    public fun raiseAll(events: Iterable<TDomainEvent>);
}
