package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.commands.EstablishBlockageCommand
import org.neptrueworks.xenohermes.domain.social.blockage.commands.InitiateUnblockageCommand
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockageThresholdExceededException
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockeeNotBlockedException
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockerAlreadyBlockedException
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialNonblockage
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCatalog
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCatalogable
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold
import org.neptrueworks.xenohermes.domain.social.blockage.params.isBlocked
import org.neptrueworks.xenohermes.domain.social.blockage.params.isMaximumBlockageExceeds
import org.neptrueworks.xenohermes.domain.social.blockage.params.isNotBlocked

public abstract class SocialBlockageCatalogingAggregateRoot: AggregateRoot(), SocialBlockageAggregatable {
    public abstract override val blocker: SocialBlockageBlocker
    public abstract override val blockageThreshold: SocialBlockageThreshold
    protected abstract val blockageCataloging: SocialBlockageCatalog;
    public final val blockageCatalog: SocialBlockageCatalogable = this.blockageCataloging;
    
    internal final fun establishBlockage(command: EstablishBlockageCommand) {
        val nonblockage = this.blockageCataloging.checkNonblockage(command.blockee);
        if (nonblockage.isBlocked())
            throw BlockerAlreadyBlockedException(command.blocker, command.blockee);
        
        nonblockage as SocialNonblockage.NotBlocked;
        if (this.blockageThreshold.isMaximumBlockageExceeds(nonblockage.blockageCount))
            throw BlockageThresholdExceededException(command.blocker, command.blockee);
        
        this.blockageCataloging.block(command.blocker, command.blockee);
    }
    
    internal final fun initiateUnblockage(command: InitiateUnblockageCommand) {
        val nonblockage = this.blockageCataloging.checkNonblockage(command.unblockee);
        if (nonblockage.isNotBlocked())
            throw BlockeeNotBlockedException(command.unblocker, command.unblockee);
        
        this.blockageCataloging.unblock(command.unblocker, command.unblockee);
    }
}
