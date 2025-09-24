package org.neptrueworks.xenohermes.domain.social.invitation.exceptions

import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAccepter
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAgent

public final class InvitationAgentBlockedAccepter internal constructor(
    val agent: SocialInvitationAgent,
    val accepter: SocialInvitationAccepter
) : SocialInvitationException()
