package org.neptrueworks.xenohermes.domain.social.request.exceptions

import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester
import org.neptrueworks.xenohermes.domain.social.request.params.SocialResponseStatus

public final class SocialRequestRespondedException internal constructor(
    val requestId: SocialRequestIdentifier,
    val requester: SocialRequestRequester,
    val agent: SocialRequestAgent,
    val responseStatus: SocialResponseStatus
) : SocialRequestException()
