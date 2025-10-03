package org.neptrueworks.xenohermes.contract.social.blockage

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.neptrueworks.xenohermes.contract.social.blockage.SocialBlockerDraft.`$`.produce
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageCatalogingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageEstablishingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.SocialBlockageEstablishingFactory
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold
import org.springframework.stereotype.Component

@Component
internal final class SocialBlockageEstablishingProducer: SocialBlockageEstablishingFactory() {
    protected override fun produceSocialBlockage(
        blocker: SocialBlockageBlocker,
        blockageThreshold: SocialBlockageThreshold
    ): SocialBlockageEstablishingAggregateRoot = SocialBlockageEstablishingAggregator(produce {
        this.blocker = blocker;
        this.blockageThreshold = blockageThreshold;
    })
}