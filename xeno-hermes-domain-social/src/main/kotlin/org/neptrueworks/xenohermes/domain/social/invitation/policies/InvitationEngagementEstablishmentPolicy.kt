package org.neptrueworks.xenohermes.domain.social.invitation.policies

import org.neptrueworks.xenohermes.domain.common.event.DomainEventHandler
import org.neptrueworks.xenohermes.domain.social.engagement.commands.EstablishEngagementCommand
import org.neptrueworks.xenohermes.domain.social.engagement.commands.EstablishEngagementCommandHandler
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementMethod
import org.neptrueworks.xenohermes.domain.social.invitation.events.SocialInvitationAcceptedEvent
import org.springframework.stereotype.Service

@Service
public final class InvitationEngagementEstablishmentPolicy(
    private val commandHandler: EstablishEngagementCommandHandler,
): DomainEventHandler<SocialInvitationAcceptedEvent>() {
    public override fun handle(event: SocialInvitationAcceptedEvent) {
        val engager = SocialEngagementEngager(event.accepter.identifier);
        val engagee = SocialEngagementEngagee(event.agent.identifier);
        val method = SocialEngagementMethod.Invitation(event.invitationId);
        this.commandHandler.handle(EstablishEngagementCommand(engager, engagee, method));
    }
}