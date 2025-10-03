package org.neptrueworks.xenohermes.domain.social.engagement

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.engagement.commands.*
import org.neptrueworks.xenohermes.domain.social.engagement.exceptions.*
import org.neptrueworks.xenohermes.domain.social.engagement.params.*

public abstract class SocialEngagementCatalogingAggregateRoot : AggregateRoot(), SocialEngagementAggregatable {
    public abstract override val engager: SocialEngagementEngager
    public abstract override val engagementThreshold: SocialEngagementThreshold
    public abstract override val requestEngagementPrivilege: SocialRequestEngagementPrivilege
    public abstract override val invitationEngagementPrivilege: SocialInvitationEngagementPrivilege
    protected abstract val engagementCataloging: SocialEngagementCatalog
    public final val engagementCatalog: SocialEngagementCatalogable = this.engagementCataloging
    
    internal final fun establishEngagement(command: EstablishEngagementCommand) {
        val nonengagement = this.engagementCataloging.checkNonengagement(command.engagee);
        if (nonengagement.isEngaged()) 
            throw EngageeAlreadyEngagedException(command.engager, command.engagee);
        
        nonengagement as SocialNonengagement.NotEngaged;
        if (nonengagement.isEngagerMaximumEngagementExceeds())
            throw EngagerEngagementThresholdExceededException(command.engager, this.engagementThreshold);
        if (nonengagement.isEngageeMaximumEngagementExceeds())
            throw EngageeEngagementThresholdExceededException(command.engagee, nonengagement.engageeEngagementThreshold);

        this.engagementCataloging.engage(command.engager, command.engagee);
    }

    internal final fun dissolveEngagement(command: DissolveEngagementCommand) {
        val nonengagement = this.engagementCataloging.checkNonengagement(command.disengagee);
        if (nonengagement.isNotEngaged())
            throw DisengageeNotEngagedException(command.disengager);

        this.engagementCataloging.disengage(command.disengager, command.disengagee);
    }

    private final inline fun SocialNonengagement.NotEngaged.isEngagerMaximumEngagementExceeds() =
        engagementThreshold.isMaximumEngagementExceeds(this.engagerEngagementCount)
    private final inline fun SocialNonengagement.NotEngaged.isEngageeMaximumEngagementExceeds() =
        this.engageeEngagementThreshold.isMaximumEngagementExceeds(this.engageeEngagementCount)
}