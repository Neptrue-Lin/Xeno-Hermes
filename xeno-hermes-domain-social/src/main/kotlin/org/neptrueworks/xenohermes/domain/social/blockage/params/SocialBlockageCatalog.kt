package org.neptrueworks.xenohermes.domain.social.blockage.params

public abstract class SocialBlockageCatalog {
    public abstract fun checkBlockage(blockee: SocialBlockageBlockee): SocialBlockage;
    public abstract fun block(blocker: SocialBlockageBlocker, blockee: SocialBlockageBlockee);
    public abstract fun unblock(unblocker: SocialBlockageBlocker, unblockee: SocialBlockageBlockee);
}