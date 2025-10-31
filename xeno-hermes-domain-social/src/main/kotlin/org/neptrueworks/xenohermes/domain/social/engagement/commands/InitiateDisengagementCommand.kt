package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.DisengagementInitiatedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialDisengagementDateTime
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

public data class InitiateDisengagementCommand(
    val disengager: SocialEngagementEngager,
    val disengagee: SocialEngagementEngagee,
    val disengagementDateTime: SocialDisengagementDateTime,
) : SocialEngagementCommand

@Service
public final class InitiateDisengagementCommandHandler(
    private val repository: SocialEngagementCatalogingRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
) : CommandHandler<InitiateDisengagementCommand>() {
    public override fun handle(command: InitiateDisengagementCommand) {
        val socialEngagement = this.repository.fetchByIdentifier(command.disengager, command.disengagee);
        socialEngagement.initiateDisengagement(command);
        this.eventTrigger.raise(DisengagementInitiatedEvent.initialize(command));
        this.repository.reposit(socialEngagement);
    }
}
