package org.neptrueworks.xenohermes.domain.social.request.exceptions

import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestExpiryPeriod
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRequester

public final class SocialRequestExpiredException internal constructor(
    val requestId: SocialRequestIdentifier,
    val requester: SocialRequestRequester,
    val agent: SocialRequestAgent,
    val expiredAt: SocialRequestExpiryPeriod
) : SocialRequestException()
