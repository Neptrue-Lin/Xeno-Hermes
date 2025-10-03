package org.neptrueworks.xenohermes.domain.social.blockage.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.blockage.events.InterlocutorBlockedEvent
import org.neptrueworks.xenohermes.domain.social.blockage.events.SocialBlockageEvent
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageDateTime
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold
import org.springframework.stereotype.Service

public data class BlockInterlocutorCommand(
    val blocker: SocialBlockageBlocker,
    val blockee: SocialBlockageBlockee,
    val blockageThreshold: SocialBlockageThreshold,
    val blockageDateTime: SocialBlockageDateTime
) : SocialBlockageCommand

@Service
public final class BlockInterlocutorCommandHandler(
    private val repository: SocialBlockageCatalogingRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialBlockageEvent>
) : CommandHandler<BlockInterlocutorCommand>() {
    public override fun handle(command: BlockInterlocutorCommand) {
        val socialBlockage = this.repository.fetchByIdentifier(command.blocker, command.blockee);
        socialBlockage.blockInterlocutor(command);
        this.eventTrigger.raise(InterlocutorBlockedEvent.initialize(command));
        this.repository.reposit(socialBlockage);
    }
}
