package org.neptrueworks.xenohermes.contract.social.request

import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.neptrueworks.xenohermes.domain.social.request.SocialRequestResponseAggregateRoot

internal final class SocialRequestResponseAggregator(
    base: SocialRequest?,
    draft: SocialRequestingDraft = SocialRequestingDraft(DraftContext(null), base),
) : SocialRequestResponseAggregateRoot(), DraftSpi by draft, SocialRequestDraft by draft