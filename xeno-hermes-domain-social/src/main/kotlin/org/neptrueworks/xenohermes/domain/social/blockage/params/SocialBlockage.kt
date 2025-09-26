package org.neptrueworks.xenohermes.domain.social.blockage.params

public sealed class SocialBlockage {
    public data object Blocked : SocialBlockage();
    public data class NotBlocked(val blockageCount: SocialBlockageCount) : SocialBlockage();
}

public inline fun SocialBlockage.isBlocked() = this is SocialBlockage.Blocked;
public inline fun SocialBlockage.isNotBlocked() = this is SocialBlockage.NotBlocked;
