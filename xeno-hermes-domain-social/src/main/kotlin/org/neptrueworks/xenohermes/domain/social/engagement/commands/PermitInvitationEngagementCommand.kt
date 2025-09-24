package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialInvitationEngagementPermittedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

public data class PermitInvitationEngagementCommand(
    val engager: SocialEngagementEngager,
) : SocialEngagementCommand

@Service
public final class PermitInvitationEngagementCommandHandler(
    private val repository: SocialEngagementRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
) : CommandHandler<PermitInvitationEngagementCommand>() {
    public override fun handle(command: PermitInvitationEngagementCommand) {
        val socialEngagement = this.repository.fetchByIdentifier(command.engager);
        socialEngagement.permitInvitationEngagement(command);
        this.eventTrigger.raise(SocialInvitationEngagementPermittedEvent.initialize(command));
        this.repository.reposit(socialEngagement);
    }
}