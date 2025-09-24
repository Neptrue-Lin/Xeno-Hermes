package org.neptrueworks.xenohermes.domain.social.engagement.params

public abstract class SocialEngagementManifest {
    public abstract val engagerEngagementCount: SocialEngagementCount;
    public abstract val engageeEngagementCount: SocialEngagementCount;

    public abstract fun isEngaged(engagee: SocialEngagementEngagee): Boolean;
    public final fun isNotEngaged(disengagee: SocialEngagementEngagee) = !this.isEngaged(disengagee);
    public abstract fun engage(engager: SocialEngagementEngager, engagee: SocialEngagementEngagee);
    public abstract fun disengage(disengager: SocialEngagementEngager, disengagee: SocialEngagementEngagee);
}