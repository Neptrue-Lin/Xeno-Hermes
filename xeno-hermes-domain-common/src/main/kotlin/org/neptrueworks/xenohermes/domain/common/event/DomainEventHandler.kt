package org.neptrueworks.xenohermes.domain.common.event

import org.neptrueworks.xenohermes.domain.common.models.DomainService

public abstract class DomainEventHandler<TDomainEvent : DomainEvent> : DomainService {
    public abstract fun handle(event: TDomainEvent)
}