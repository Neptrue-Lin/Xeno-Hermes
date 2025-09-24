package org.neptrueworks.xenohermes.domain.social.invitation.events

import org.neptrueworks.xenohermes.domain.social.invitation.commands.IssueSocialInvitationCommand
import org.neptrueworks.xenohermes.domain.social.invitation.params.*

public data class SocialInvitationIssuedEvent private constructor(
    val issuer: SocialInvitationIssuer,
    val agent: SocialInvitationAgent,
    val audience: SocialInvitationAudience,
    val activePeriod: SocialInvitationActivePeriod,
    val expiryPeriod: SocialInvitationExpiryPeriod,
) : SocialInvitationEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: IssueSocialInvitationCommand) = SocialInvitationIssuedEvent(
            issuer = command.issuer,
            agent = command.agent,
            audience = command.audience,
            activePeriod = command.activePeriod,
            expiryPeriod = command.expiryPeriod,
        )
    }
}
