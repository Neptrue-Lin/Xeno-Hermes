package org.neptrueworks.xenohermes.domain.social.request.exceptions

import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestAgent
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevoker

public final class SocialRequestRevocationForbiddenException internal constructor(
    val requestId: SocialRequestIdentifier,
    val revoker: SocialRequestRevoker,
    val agent: SocialRequestAgent,
) : SocialRequestException()
