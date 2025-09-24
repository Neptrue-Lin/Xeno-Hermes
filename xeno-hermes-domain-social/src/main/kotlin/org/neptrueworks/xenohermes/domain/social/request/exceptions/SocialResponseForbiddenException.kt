package org.neptrueworks.xenohermes.domain.social.request.exceptions

import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestResponder

public final class SocialResponseForbiddenException internal constructor(
    val requestId: SocialRequestIdentifier,
    val responder: SocialRequestResponder,
    val agent: SocialRequestAgent,
) : SocialRequestException() 
