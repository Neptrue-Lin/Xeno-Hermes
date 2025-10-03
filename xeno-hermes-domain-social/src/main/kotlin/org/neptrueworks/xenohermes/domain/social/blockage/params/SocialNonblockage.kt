package org.neptrueworks.xenohermes.domain.social.blockage.params

public sealed class SocialNonblockage {
    public data object Blocked : SocialNonblockage();
    public data class NotBlocked(val blockageCount: SocialBlockageCount) : SocialNonblockage();
}

public inline fun SocialNonblockage.isBlocked() = this is SocialNonblockage.Blocked;
public inline fun SocialNonblockage.isNotBlocked() = this is SocialNonblockage.NotBlocked;
