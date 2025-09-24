package org.neptrueworks.xenohermes.domain.social.request.events

import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.commands.AcceptSocialRequestCommand
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestResponder

public data class SocialRequestAcceptedEvent private constructor(
    val requestId: SocialRequestIdentifier,
    val requester: SocialRequestRequester,
    val agent: SocialRequestAgent,
    val responder: SocialRequestResponder,
) : SocialRequestEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: AcceptSocialRequestCommand, requester: SocialRequestRequester, agent: SocialRequestAgent) =
            SocialRequestAcceptedEvent(
                requestId = command.requestId,
                requester = requester,
                agent = agent,
                responder = command.responder,
            )
    }
}
