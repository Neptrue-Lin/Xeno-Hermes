package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.InterlocutorDisengagedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialDisengagementDateTime
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.springframework.stereotype.Service

public data class DisengageInterlocutorCommand(
    val disengager: SocialEngagementEngager,
    val disengagee: SocialEngagementEngagee,
    val disengagementDateTime: SocialDisengagementDateTime,
) : SocialEngagementCommand

@Service
public final class DisengageInterlocutorCommandHandler(
    private val repository: SocialEngagementRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
) : CommandHandler<DisengageInterlocutorCommand>() {
    public override fun handle(command: DisengageInterlocutorCommand) {
        val socialEngagement = this.repository.fetchByIdentifier(command.disengager);
        socialEngagement.disengageInterlocutor(command);
        this.eventTrigger.raise(InterlocutorDisengagedEvent.initialize(command));
        this.repository.reposit(socialEngagement);
    }
}
