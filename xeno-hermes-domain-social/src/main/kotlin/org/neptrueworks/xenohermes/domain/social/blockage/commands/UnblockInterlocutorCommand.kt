package org.neptrueworks.xenohermes.domain.social.blockage.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageRepositable
import org.neptrueworks.xenohermes.domain.social.blockage.events.InterlocutorUnblockedEvent
import org.neptrueworks.xenohermes.domain.social.blockage.events.SocialBlockageEvent
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.springframework.stereotype.Service

public data class UnblockInterlocutorCommand(
    val unblocker: SocialBlockageBlocker,
    val unblockee: SocialBlockageBlockee,
) : SocialBlockageCommand

@Service
public final class UnblockInterlocutorCommandHandler(
    private val repository: SocialBlockageRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialBlockageEvent>
) : CommandHandler<UnblockInterlocutorCommand>() {
    public override fun handle(command: UnblockInterlocutorCommand) {
        val socialBlockage = this.repository.fetchByIdentifier(command.unblocker);
        socialBlockage.unblockInterlocutor(command);
        this.eventTrigger.raise(InterlocutorUnblockedEvent.initialize(command));
        this.repository.reposit(socialBlockage);
    }
}
