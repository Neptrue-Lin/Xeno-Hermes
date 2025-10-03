package org.neptrueworks.xenohermes.contract.social.blockage.params

import org.neptrueworks.xenohermes.contract.social.blockage.SocialBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialNonblockage
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCatalog

internal final class SocialBlockageCataloger(
    blocker: SocialBlocker,
    nonblockage: SocialBlocker? 
) : SocialBlockageCatalog() {
    internal var operation = if (nonblockage == null)
             SocialBlockageCatalogingOperation.CheckingBlocked
        else SocialBlockageCatalogingOperation.CheckingNotBlocked(blocker, nonblockage)
        private set;
    override fun checkNonblockage(blockee: SocialBlockageBlockee) = when(val op = this.operation){
        is SocialBlockageCatalogingOperation.CheckingBlocked -> SocialNonblockage.Blocked
        is SocialBlockageCatalogingOperation.CheckingNotBlocked -> SocialNonblockage.NotBlocked(op.blocker.blockageCount)
        else -> throw IllegalStateException("Invalid to check operation")
    }

    override fun block(blocker: SocialBlockageBlocker, blockee: SocialBlockageBlockee) = when(val op = this.operation){
        is SocialBlockageCatalogingOperation.CheckingBlocked -> {}
        is SocialBlockageCatalogingOperation.CheckingNotBlocked -> this.operation = SocialBlockageCatalogingOperation.Blocking(blocker, blockee)
        else -> throw IllegalStateException("Invalid to block")
    }

    override fun unblock(unblocker: SocialBlockageBlocker, unblockee: SocialBlockageBlockee) = when(val op = this.operation){
        is SocialBlockageCatalogingOperation.CheckingNotBlocked -> {}
        is SocialBlockageCatalogingOperation.CheckingBlocked -> this.operation = SocialBlockageCatalogingOperation.Unblocking(unblocker, unblockee)
        else -> throw IllegalStateException("Invalid to unblock")
    }
}