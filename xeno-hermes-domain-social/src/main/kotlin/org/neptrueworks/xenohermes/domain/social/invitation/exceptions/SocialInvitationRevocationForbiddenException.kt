package org.neptrueworks.xenohermes.domain.social.invitation.exceptions

import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAgent
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevoker

public final class SocialInvitationRevocationForbiddenException internal constructor(
    val invitationId: SocialInvitationIdentifier,
    val revoker: SocialInvitationRevoker,
    val agent: SocialInvitationAgent,
) : SocialInvitationException()
