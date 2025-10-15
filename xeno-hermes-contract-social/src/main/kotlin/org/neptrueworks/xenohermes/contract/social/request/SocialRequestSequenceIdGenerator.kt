package org.neptrueworks.xenohermes.contract.social.request

import org.neptrueworks.xenohermes.contract.common.manipulation.SequenceGenerator
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifierGenerator
import org.springframework.stereotype.Component

@Component
internal final class SocialRequestSequenceIdGenerator(
    private val sequenceGenerator: SequenceGenerator,
) : SocialRequestIdentifierGenerator {
    override fun nextIdentifier(): SocialRequestIdentifier {
        val sequenceName = "social_request_id_seq";
        return this.sequenceGenerator.nextValueCommand(sequenceName).execute().run(::SocialRequestIdentifier);
    }
}