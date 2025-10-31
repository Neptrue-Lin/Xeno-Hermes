package org.neptrueworks.xenohermes.domain.social.blockage.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.blockage.events.UnblockageInitiatedEvent
import org.neptrueworks.xenohermes.domain.social.blockage.events.SocialBlockageEvent
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.springframework.stereotype.Service

public data class InitiateUnblockageCommand(
    val unblocker: SocialBlockageBlocker,
    val unblockee: SocialBlockageBlockee,
) : SocialBlockageCommand

@Service
public final class InitiateUnblockageCommandHandler(
    private val repository: SocialBlockageCatalogingRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialBlockageEvent>
) : CommandHandler<InitiateUnblockageCommand>() {
    public override fun handle(command: InitiateUnblockageCommand) {
        val socialBlockage = this.repository.fetchByIdentifier(command.unblocker, command.unblockee);
        socialBlockage.initiateUnblockage(command);
        this.eventTrigger.raise(UnblockageInitiatedEvent.initialize(command));
        this.repository.reposit(socialBlockage);
    }
}
