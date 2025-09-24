package org.neptrueworks.xenohermes.domain.social.request.events

import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.commands.RejectSocialRequestCommand
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestResponder

public data class SocialRequestRejectedEvent private constructor(
    val requestId: SocialRequestIdentifier,
    val requester: SocialRequestRequester,
    val agent: SocialRequestAgent,
    val responder: SocialRequestResponder,
) : SocialRequestEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: RejectSocialRequestCommand, requester: SocialRequestRequester, agent: SocialRequestAgent) =
            SocialRequestRejectedEvent(
                requestId = command.requestId,
                requester = requester,
                agent = agent,
                responder = command.responder,
            )
    }
}
