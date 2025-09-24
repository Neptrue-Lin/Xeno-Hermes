package org.neptrueworks.xenohermes.domain.social.request.events

import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.commands.SendSocialRequestCommand
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester

public data class SocialRequestSentEvent private constructor(
    val requestId: SocialRequestIdentifier,
    val requester: SocialRequestRequester,
    val agent: SocialRequestAgent,
) : SocialRequestEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: SendSocialRequestCommand, requestId: SocialRequestIdentifier) = SocialRequestSentEvent(
            requestId = requestId, 
            requester = command.requester, 
            agent = command.agent,
        )
    }
}
