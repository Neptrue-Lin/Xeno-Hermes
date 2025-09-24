package org.neptrueworks.xenohermes.domain.social.request.events

import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.commands.RevokeSocialRequestCommand
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevoker

public data class SocialRequestRevokedEvent private constructor(
    val requestId: SocialRequestIdentifier,
    val requester: SocialRequestRequester,
    val agent: SocialRequestAgent,
    val revoker: SocialRequestRevoker,
) : SocialRequestEvent {
    internal companion object Initializer {
        internal final inline fun initialize(command: RevokeSocialRequestCommand, requester: SocialRequestRequester, agent: SocialRequestAgent) =
            SocialRequestRevokedEvent(
                requestId = command.requestId,
                requester = requester,
                agent = agent,
                revoker = command.revoker,
            )
    }
}

