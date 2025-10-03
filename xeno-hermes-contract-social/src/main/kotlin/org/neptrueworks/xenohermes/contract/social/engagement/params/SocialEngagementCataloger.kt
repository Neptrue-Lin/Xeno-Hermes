package org.neptrueworks.xenohermes.contract.social.engagement.params

import org.neptrueworks.xenohermes.contract.social.engagement.params.SocialEngagementCatalogingOperation
import org.neptrueworks.xenohermes.contract.social.engagement.SocialEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementCatalog
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngagee
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialEngagementEngager
import org.neptrueworks.xenohermes.domain.social.engagement.params.SocialNonengagement

internal final class SocialEngagementCataloger(
    engager: SocialEngager,
    nonengagement: SocialEngager?,
) : SocialEngagementCatalog() {
    public var operation = if (nonengagement == null)
         SocialEngagementCatalogingOperation.CheckingEngaged
    else SocialEngagementCatalogingOperation.CheckingNotEngaged(engager, nonengagement)
        private set;

    override fun checkNonengagement(engagee: SocialEngagementEngagee) = when (val op = this.operation) {
        is SocialEngagementCatalogingOperation.CheckingEngaged -> SocialNonengagement.Engaged;
        is SocialEngagementCatalogingOperation.CheckingNotEngaged -> SocialNonengagement.NotEngaged(
            engagerEngagementCount = op.engager.engagementCount,
            engageeEngagementCount = op.nonengagement.engagementCount,
            engageeEngagementThreshold = op.nonengagement.engagementThreshold);
        else -> throw IllegalStateException("Invalid to check engagement");
    }

    override fun engage(engager: SocialEngagementEngager, engagee: SocialEngagementEngagee) = when (this.operation) {
        is SocialEngagementCatalogingOperation.CheckingEngaged -> {}
        is SocialEngagementCatalogingOperation.CheckingNotEngaged -> this.operation =
            SocialEngagementCatalogingOperation.Engaging(engager, engagee);
        else -> throw IllegalStateException("Invalid to engage");
    }

    override fun disengage(disengager: SocialEngagementEngager, disengagee: SocialEngagementEngagee) = when (this.operation) {
        is SocialEngagementCatalogingOperation.CheckingNotEngaged -> {}
        is SocialEngagementCatalogingOperation.CheckingEngaged -> this.operation =
            SocialEngagementCatalogingOperation.Disengaging(disengager, disengagee);
        else -> throw IllegalStateException("Invalid to disengage");
    }
}