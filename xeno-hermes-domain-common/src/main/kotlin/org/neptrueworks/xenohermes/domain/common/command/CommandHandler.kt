package org.neptrueworks.xenohermes.domain.common.command

import org.neptrueworks.xenohermes.domain.common.models.DomainService

public abstract class CommandHandler<TCommand : DomainCommand> : DomainService {
    public abstract fun handle(command: TCommand);
}