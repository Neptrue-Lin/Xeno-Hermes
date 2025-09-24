package org.neptrueworks.xenohermes.domain.social.request.exceptions

import org.neptrueworks.xenohermes.domain.social.request.SocialRequestIdentifier
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestRevoker

public final class SocialRequestNotSentException internal constructor(
    val requestId: SocialRequestIdentifier,
    val revoker: SocialRequestRevoker,
) : SocialRequestException()
