package org.neptrueworks.xenohermes.domain.social.engagement.params

public abstract class SocialEngagementCatalog {
    public abstract fun checkEngagement(engagee: SocialEngagementEngagee): SocialEngagement;
    public abstract fun engage(engager: SocialEngagementEngager, engagee: SocialEngagementEngagee);
    public abstract fun disengage(disengager: SocialEngagementEngager, disengagee: SocialEngagementEngagee);
}