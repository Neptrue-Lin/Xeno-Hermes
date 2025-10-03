package org.neptrueworks.xenohermes.contract.social.request

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestResponseAggregateRoot
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestSendingAggregateRoot

internal final class SocialRequestSendingAggregator(
    base: SocialRequest?,
    draft: SocialRequestingDraft = SocialRequestingDraft(DraftContext(null), base),
) : SocialRequestSendingAggregateRoot(), DraftSpi by draft, SocialRequestDraft by draft {
    internal final fun resolve() = this.__resolve() as SocialRequest;
}