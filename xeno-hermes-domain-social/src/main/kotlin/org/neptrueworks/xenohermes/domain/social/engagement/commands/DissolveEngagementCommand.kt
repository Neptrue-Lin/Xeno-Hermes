package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.InterlocutorDisengagedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialDisengagementDateTime
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

public data class DissolveEngagementCommand(
    val disengager: SocialEngagementEngager,
    val disengagee: SocialEngagementEngagee,
    val disengagementDateTime: SocialDisengagementDateTime,
) : SocialEngagementCommand

@Service
public final class DissolveEngagementCommandHandler(
    private val repository: SocialEngagementCatalogingRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
) : CommandHandler<DissolveEngagementCommand>() {
    public override fun handle(command: DissolveEngagementCommand) {
        val socialEngagement = this.repository.fetchByIdentifier(command.disengager, command.disengagee);
        socialEngagement.dissolveEngagement(command);
        this.eventTrigger.raise(InterlocutorDisengagedEvent.initialize(command));
        this.repository.reposit(socialEngagement);
    }
}
