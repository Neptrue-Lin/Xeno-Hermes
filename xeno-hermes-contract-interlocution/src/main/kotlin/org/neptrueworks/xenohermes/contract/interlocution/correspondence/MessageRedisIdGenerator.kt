package org.neptrueworks.xenohermes.contract.interlocution.correspondence

import org.neptrueworks.xenohermes.contract.common.manipulation.RedisKeyGenerator
import org.neptrueworks.xenohermes.domain.interlocution.conversation.ConversationIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.MessageIdentifierGeneratable
import org.springframework.stereotype.Component

@Component
internal final class MessageRedisIdGenerator(
    private val redisKeyGenerator: RedisKeyGenerator,
) : MessageIdentifierGeneratable {
    override fun nextIdentifier(conversation: ConversationIdentifier): MessageIdentifier {
        val key = "xenohermes:message:correspondence:${conversation}";
        return this.redisKeyGenerator.nextValueCommand(key).execute().run(::MessageIdentifier);
    }
}