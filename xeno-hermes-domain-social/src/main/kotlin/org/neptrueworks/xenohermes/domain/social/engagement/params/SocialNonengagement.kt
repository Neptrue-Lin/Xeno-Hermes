package org.neptrueworks.xenohermes.domain.social.engagement.params

public sealed class SocialNonengagement {
    public data object Engaged : SocialNonengagement() 
    public data class NotEngaged(
        val engagerEngagementCount: SocialEngagementCount,
        val engageeEngagementCount: SocialEngagementCount,
        val engageeEngagementThreshold: SocialEngagementThreshold
    ) : SocialNonengagement()
}

public inline fun SocialNonengagement.isEngaged() = this is SocialNonengagement.Engaged;
public inline fun SocialNonengagement.isNotEngaged() = this is SocialNonengagement.NotEngaged;