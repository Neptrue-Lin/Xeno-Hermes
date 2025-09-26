package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.commands.BlockInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.blockage.commands.UnblockInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockageThresholdExceededException
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockeeNotBlockedException
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockerAlreadyBlockedException
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockage
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCatalog
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCount
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold
import org.neptrueworks.xenohermes.domain.social.blockage.params.isBlocked
import org.neptrueworks.xenohermes.domain.social.blockage.params.isMaximumBlockageExceeds
import org.neptrueworks.xenohermes.domain.social.blockage.params.isNotBlocked

public abstract class SocialBlockageAggregateRoot: AggregateRoot(), SocialBlockageAggregatable {
    public abstract override val blocker: SocialBlockageBlocker
    public abstract override val blockageThreshold: SocialBlockageThreshold
    protected abstract val blockageCatalog: SocialBlockageCatalog;
    
    public final fun checkBlockage(blockee: SocialBlockageBlockee) = 
        this.blockageCatalog.checkBlockage(blockee);
    
    internal final fun blockInterlocutor(command: BlockInterlocutorCommand) {
        val blockage = this.blockageCatalog.checkBlockage(command.blockee);
        if (blockage.isBlocked())
            throw BlockerAlreadyBlockedException(command.blocker, command.blockee);
        
        blockage as SocialBlockage.NotBlocked;
        if (this.blockageThreshold.isMaximumBlockageExceeds(blockage.blockageCount))
            throw BlockageThresholdExceededException(command.blocker, command.blockee);
        
        this.blockageCatalog.block(command.blocker, command.blockee);
    }
    
    internal final fun unblockInterlocutor(command: UnblockInterlocutorCommand) {
        val blockage = this.blockageCatalog.checkBlockage(command.unblockee);
        if (blockage.isNotBlocked())
            throw BlockeeNotBlockedException(command.unblocker, command.unblockee);
        
        this.blockageCatalog.unblock(command.unblocker, command.unblockee);
    }
}
