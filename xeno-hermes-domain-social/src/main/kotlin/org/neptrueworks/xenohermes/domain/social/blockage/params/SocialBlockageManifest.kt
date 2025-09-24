package org.neptrueworks.xenohermes.domain.social.blockage.params

public abstract class SocialBlockageManifest {
    public abstract val blockerBlockageCount: SocialBlockageCount;
    public abstract val blockeeBlockageCount: SocialBlockageCount;

    public abstract fun isBlocked(blockee: SocialBlockageBlockee): Boolean;
    public final inline fun isNotBlocked(unblockee: SocialBlockageBlockee) = !this.isBlocked(unblockee);
    public abstract fun block(blocker: SocialBlockageBlocker, blockee: SocialBlockageBlockee);
    public abstract fun unblock(unblocker: SocialBlockageBlocker, unblockee: SocialBlockageBlockee);
}