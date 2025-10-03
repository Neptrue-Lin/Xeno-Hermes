package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRootFactory
import org.neptrueworks.xenohermes.domain.social.blockage.commands.BlockInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold
import org.springframework.stereotype.Component

@Component
public abstract class SocialBlockageEstablishingFactory: AggregateRootFactory() {
    internal final fun establishBlockage(command: BlockInterlocutorCommand): SocialBlockageEstablishingAggregateRoot{
        return produceSocialBlockage(
            blocker = command.blocker,
            blockageThreshold = command.blockageThreshold,
        )
    }
    
    protected abstract fun produceSocialBlockage(
        blocker: SocialBlockageBlocker,
        blockageThreshold: SocialBlockageThreshold,
    ): SocialBlockageEstablishingAggregateRoot
}
