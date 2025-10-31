package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRestrictionRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.InvitationEngagementPermittedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

public data class PermitInvitationEngagementCommand(
    val engager: SocialEngagementEngager,
) : SocialEngagementCommand

@Service
public final class PermitInvitationEngagementCommandHandler(
    private val repository: SocialEngagementRestrictionRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
) : CommandHandler<PermitInvitationEngagementCommand>() {
    public override fun handle(command: PermitInvitationEngagementCommand) {
        val engagementRestriction = this.repository.fetchByIdentifier(command.engager);
        engagementRestriction.permitInvitationEngagement(command);
        this.eventTrigger.raise(InvitationEngagementPermittedEvent.initialize(command));
        this.repository.reposit(engagementRestriction);
    }
}