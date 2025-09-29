package org.neptrueworks.xenohermes.domain.social.engagement.params

import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier

public sealed class SocialEngagementMethod {
    public data class Request(val requestId: SocialRequestIdentifier) : SocialEngagementMethod();
    public data class Invitation(val invitationId: SocialInvitationIdentifier) : SocialEngagementMethod();
}
