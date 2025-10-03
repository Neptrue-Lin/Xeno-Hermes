package org.neptrueworks.xenohermes.domain.social.engagement.params

public sealed interface SocialEngagementCatalogable {
    public abstract fun checkNonengagement(engagee: SocialEngagementEngagee): SocialNonengagement;
}

public abstract class SocialEngagementCatalog : SocialEngagementCatalogable{
    public abstract fun engage(engager: SocialEngagementEngager, engagee: SocialEngagementEngagee);
    public abstract fun disengage(disengager: SocialEngagementEngager, disengagee: SocialEngagementEngagee);
}