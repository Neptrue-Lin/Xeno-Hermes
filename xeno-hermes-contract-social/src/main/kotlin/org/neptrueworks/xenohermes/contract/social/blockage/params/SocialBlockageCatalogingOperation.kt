package org.neptrueworks.xenohermes.contract.social.blockage.params

import org.neptrueworks.xenohermes.contract.social.blockage.SocialBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker

internal sealed class SocialBlockageCatalogingOperation {
    internal final data object CheckingBlocked: SocialBlockageCatalogingOperation();
    internal final data class CheckingNotBlocked(val blocker: SocialBlocker, val blockee: SocialBlocker) :
        SocialBlockageCatalogingOperation();
    internal final data class Blocking(val blocker: SocialBlockageBlocker, val blockee: SocialBlockageBlockee) :
        SocialBlockageCatalogingOperation();
    internal final data class Unblocking(val unblocker: SocialBlockageBlocker, val unblockee: SocialBlockageBlockee) :
        SocialBlockageCatalogingOperation();
}