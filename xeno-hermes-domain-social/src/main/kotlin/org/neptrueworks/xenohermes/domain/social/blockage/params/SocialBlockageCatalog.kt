package org.neptrueworks.xenohermes.domain.social.blockage.params

public sealed interface SocialBlockageCatalogable {
    public abstract fun checkNonblockage(blockee: SocialBlockageBlockee): SocialNonblockage;
}

public abstract class SocialBlockageCatalog: SocialBlockageCatalogable {
    public abstract fun block(blocker: SocialBlockageBlocker, blockee: SocialBlockageBlockee);
    public abstract fun unblock(unblocker: SocialBlockageBlocker, unblockee: SocialBlockageBlockee);
}