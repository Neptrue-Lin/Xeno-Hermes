package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementCatalogingRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRestrictionRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialRequestEngagementPermittedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

public data class PermitRequestEngagementCommand(
    val engager: SocialEngagementEngager,
) : SocialEngagementCommand

@Service
public final class PermitRequestEngagementCommandHandler(
    private val repository: SocialEngagementRestrictionRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
) : CommandHandler<PermitRequestEngagementCommand>() {
    public override fun handle(command: PermitRequestEngagementCommand) {
        val engagementRestriction = this.repository.fetchByIdentifier(command.engager);
        engagementRestriction.permitRequestEngagement(command);
        this.eventTrigger.raise(SocialRequestEngagementPermittedEvent.initialize(command));
        this.repository.reposit(engagementRestriction);
    }
}