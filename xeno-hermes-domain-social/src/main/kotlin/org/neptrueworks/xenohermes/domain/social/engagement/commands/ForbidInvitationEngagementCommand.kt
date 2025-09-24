package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialInvitationEngagementForbiddenEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

public data class ForbidInvitationEngagementCommand(
    val engager: SocialEngagementEngager,
) : SocialEngagementCommand

@Service
public final class ForbidInvitationEngagementCommandHandler(
    private val repository: SocialEngagementRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
) : CommandHandler<ForbidInvitationEngagementCommand>() {
    public override fun handle(command: ForbidInvitationEngagementCommand) {
        val socialEngagement = this.repository.fetchByIdentifier(command.engager);
        socialEngagement.forbidInvitationEngagement(command);
        this.eventTrigger.raise(SocialInvitationEngagementForbiddenEvent.initialize(command));
        this.repository.reposit(socialEngagement);
    }
}