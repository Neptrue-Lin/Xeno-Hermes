package org.neptrueworks.xenohermes.contract.social.invitation

import org.neptrueworks.xenohermes.contract.common.manipulation.SequenceGenerator
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifierGenerator
import org.springframework.stereotype.Component

@Component
internal final class SocialInvitationSequenceIdGenerator(
    private val sequenceGenerator: SequenceGenerator,
) : SocialInvitationIdentifierGenerator {
    override fun nextIdentifier(): SocialInvitationIdentifier {
        val sequenceName = "social_invitation_id_seq";
        return this.sequenceGenerator.nextValueCommand(sequenceName).execute().run(::SocialInvitationIdentifier);
    }
}