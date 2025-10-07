package org.neptrueworks.xenohermes.contract.social.blockage.params

import org.neptrueworks.xenohermes.contract.social.blockage.SocialBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker

internal sealed class SocialBlockageCatalogingOperation {
    internal data object CheckingBlocked: SocialBlockageCatalogingOperation();
    internal data class CheckingNotBlocked(val blocker: SocialBlocker, val blockee: SocialBlocker) : SocialBlockageCatalogingOperation();
    internal data class Blocking(val blocker: SocialBlockageBlocker, val blockee: SocialBlockageBlockee) : SocialBlockageCatalogingOperation();
    internal data class Unblocking(val unblocker: SocialBlockageBlocker, val unblockee: SocialBlockageBlockee) : SocialBlockageCatalogingOperation();
}