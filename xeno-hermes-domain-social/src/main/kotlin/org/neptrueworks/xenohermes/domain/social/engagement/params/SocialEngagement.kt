package org.neptrueworks.xenohermes.domain.social.engagement.params

public sealed class SocialEngagement {
    public data object Engaged : SocialEngagement() 
    public data class NotEngaged(
        val engagerEngagementCount: SocialEngagementCount,
        val engageeEngagementCount: SocialEngagementCount,
        val engageeEngagementThreshold: SocialEngagementThreshold
    ) : SocialEngagement()
}

public inline fun SocialEngagement.isEngaged() = this is SocialEngagement.Engaged
public inline fun SocialEngagement.isNotEngaged() = this is SocialEngagement.NotEngaged