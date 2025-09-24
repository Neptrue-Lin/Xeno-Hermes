package org.neptrueworks.xenohermes.domain.social.invitation.params

import org.neptrueworks.xenohermes.domain.common.models.InlineClass

@InlineClass
public data class SocialInvitationAudience(val identifier: Long) {
    public final fun isAudience(accepter: SocialInvitationAccepter) =
        this.identifier == accepter.identifier
    public final fun isNotAudience(accepter: SocialInvitationAccepter) =
        this.identifier != accepter.identifier
}