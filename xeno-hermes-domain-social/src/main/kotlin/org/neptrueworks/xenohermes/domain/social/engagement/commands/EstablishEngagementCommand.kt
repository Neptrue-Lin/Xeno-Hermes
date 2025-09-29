package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.InterlocutorEngagedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementMethod
import org.springframework.stereotype.Service

public data class EstablishEngagementCommand(
    val engager: SocialEngagementEngager,
    val engagee: SocialEngagementEngagee,
    val method: SocialEngagementMethod,
) : SocialEngagementCommand

@Service
public final class EstablishEngagementCommandHandler(
    private val repository: SocialEngagementCatalogingRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
): CommandHandler<EstablishEngagementCommand>() {
    public override fun handle(command: EstablishEngagementCommand) {
        val engagementCataloging = this.repository.fetchByIdentifier(command.engager, command.engagee);
        engagementCataloging.establishEngagement(command);
        this.eventTrigger.raise(InterlocutorEngagedEvent.initialize(command));
        this.repository.reposit(engagementCataloging);
    }
}
