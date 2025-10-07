package org.neptrueworks.xenohermes.contract.social.engagement.params

import org.neptrueworks.xenohermes.contract.social.engagement.SocialEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager

internal sealed class SocialEngagementCatalogingOperation {
    internal data object CheckingEngaged : SocialEngagementCatalogingOperation();
    internal data class CheckingNotEngaged(val engager: SocialEngager, val nonengagement: SocialEngager) : SocialEngagementCatalogingOperation();
    internal data class Engaging(val engager: SocialEngagementEngager, val engagee: SocialEngagementEngagee) : SocialEngagementCatalogingOperation();
    internal data class Disengaging(val engager: SocialEngagementEngager, val engagee: SocialEngagementEngagee) : SocialEngagementCatalogingOperation();
}