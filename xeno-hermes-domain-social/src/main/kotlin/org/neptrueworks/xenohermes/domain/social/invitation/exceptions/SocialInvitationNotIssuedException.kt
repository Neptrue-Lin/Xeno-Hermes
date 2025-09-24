package org.neptrueworks.xenohermes.domain.social.invitation.exceptions

import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier

public final class SocialInvitationNotIssuedException internal constructor(
    val invitationId: SocialInvitationIdentifier,
) : SocialInvitationException()
