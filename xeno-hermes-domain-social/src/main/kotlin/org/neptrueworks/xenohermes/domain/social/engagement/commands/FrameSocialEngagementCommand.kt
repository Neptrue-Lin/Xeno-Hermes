package org.neptrueworks.xenohermes.domain.social.engagement.commands

import org.neptrueworks.xenohermes.domain.common.command.CommandHandler
import org.neptrueworks.xenohermes.domain.common.event.DomainEventRaiseable
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementFactory
import org.neptrueworks.xenohermes.domain.social.engagement.SocialEngagementRepositable
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementEvent
import org.neptrueworks.xenohermes.domain.social.engagement.events.SocialEngagementFramedEvent
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementThreshold
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialInvitationEngagementPrivilege
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialRequestEngagementPrivilege
import org.springframework.stereotype.Service

public data class FrameSocialEngagementCommand(
    val engager: SocialEngagementEngager,
    val requestEngagementPrivilege: SocialRequestEngagementPrivilege,
    val invitationEngagementPrivilege: SocialInvitationEngagementPrivilege,
    val engagerEngagementThreshold: SocialEngagementThreshold,
) : SocialEngagementCommand

@Service
public final class FrameSocialActorCommandHandler(
    private val factory: SocialEngagementFactory,
    private val repository: SocialEngagementRepositable,
    private val eventTrigger: DomainEventRaiseable<SocialEngagementEvent>
) : CommandHandler<FrameSocialEngagementCommand>() {
    public override fun handle(command: FrameSocialEngagementCommand) {
        val socialEngagement = this.factory.frameActor(command);
        this.eventTrigger.raise(SocialEngagementFramedEvent.initialize(command));
        this.repository.reposit(socialEngagement);
    }
}
