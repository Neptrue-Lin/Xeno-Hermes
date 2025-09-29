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
    protected abstract val engagementCatalog: SocialEngagementCatalog
    
    public final fun checkEngagement(engagee: SocialEngagementEngagee) =
        this.engagementCatalog.checkEngagement(engagee)

    internal final fun establishEngagement(command: EstablishEngagementCommand) {
        val engagement = this.engagementCatalog.checkEngagement(command.engagee);
        if (engagement.isEngaged()) 
            throw EngageeAlreadyEngagedException(command.engager, command.engagee);
        
        engagement as SocialEngagement.NotEngaged;
        if (engagement.isEngagerMaximumEngagementExceeds())
            throw EngagerEngagementThresholdExceededException(command.engager, this.engagementThreshold);
        if (engagement.isEngageeMaximumEngagementExceeds())
            throw EngageeEngagementThresholdExceededException(command.engagee, engagement.engageeEngagementThreshold);

        this.engagementCatalog.engage(command.engager, command.engagee);
    }

    internal final fun dissolveEngagement(command: DissolveEngagementCommand) {
        val engagement = this.engagementCatalog.checkEngagement(command.disengagee);
        if (engagement.isNotEngaged())
            throw DisengageeNotEngagedException(command.disengager);

        this.engagementCatalog.disengage(command.disengager, command.disengagee);
    }

    private final inline fun SocialEngagement.NotEngaged.isEngagerMaximumEngagementExceeds() =
        engagementThreshold.isMaximumEngagementExceeds(this.engagerEngagementCount)
    private final inline fun SocialEngagement.NotEngaged.isEngageeMaximumEngagementExceeds() =
        this.engageeEngagementThreshold.isMaximumEngagementExceeds(this.engageeEngagementCount)
}