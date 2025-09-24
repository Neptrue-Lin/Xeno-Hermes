package org.neptrueworks.xenohermes.domain.social.request.exceptions

import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester

public final class RequestAgentAlreadyEngagedException internal constructor(
    val agent: SocialRequestAgent,
    val requester: SocialRequestRequester
) : SocialRequestException()