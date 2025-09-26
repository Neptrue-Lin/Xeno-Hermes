package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.models.DomainService
import org.neptrueworks.xenohermes.domain.social.blockage.commands.BlockInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCount
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold
import org.springframework.stereotype.Component

@Component
public abstract class SocialBlockageFactory: DomainService {
    internal final fun establishBlockage(command: BlockInterlocutorCommand): SocialBlockageAggregateRoot {
        return produceSocialBlockage(
            blocker = command.blocker,
            blockageThreshold = command.blockageThreshold,
        )
    }
    
    protected abstract fun produceSocialBlockage(
        blocker: SocialBlockageBlocker,
        blockageThreshold: SocialBlockageThreshold,
    ): SocialBlockageAggregateRoot
}
