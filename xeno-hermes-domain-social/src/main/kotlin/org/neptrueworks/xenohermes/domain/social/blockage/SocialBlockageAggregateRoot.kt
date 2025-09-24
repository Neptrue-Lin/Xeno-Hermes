package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.commands.BlockInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.blockage.commands.UnblockInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockageThresholdExceededException
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockeeNotBlockedException
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockerAlreadyBlockedException
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageManifest
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold
import org.neptrueworks.xenohermes.domain.social.blockage.params.isMaximumBlockageExceeds

public abstract class SocialBlockageAggregateRoot: AggregateRoot(), SocialBlockageAggregatable {
    public abstract override val blocker: SocialBlockageBlocker
    public abstract override val blockageManifest: SocialBlockageManifest
    public abstract override val blockageThreshold: SocialBlockageThreshold
    
    internal final fun blockInterlocutor(command: BlockInterlocutorCommand) {
        if (this.blockageManifest.isBlocked(command.blockee))
            throw BlockerAlreadyBlockedException(command.blocker, command.blockee);
        if (this.blockageThreshold.isMaximumBlockageExceeds(this.blockageManifest.blockeeBlockageCount))
            throw BlockageThresholdExceededException(command.blocker, command.blockee);
        
        this.blockageManifest.block(command.blocker, command.blockee);
    }
    
    internal final fun unblockInterlocutor(command: UnblockInterlocutorCommand) {
        if (this.blockageManifest.isNotBlocked(command.unblockee))
            throw BlockeeNotBlockedException(command.unblocker, command.unblockee);
        
        this.blockageManifest.unblock(command.unblocker, command.unblockee);
    }
}
