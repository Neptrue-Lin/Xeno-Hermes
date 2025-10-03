package org.neptrueworks.xenohermes.domain.social.blockage

import org.neptrueworks.xenohermes.domain.common.aggregation.AggregateRoot
import org.neptrueworks.xenohermes.domain.social.blockage.commands.BlockInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.blockage.commands.UnblockInterlocutorCommand
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockageThresholdExceededException
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockeeNotBlockedException
import org.neptrueworks.xenohermes.domain.social.blockage.exceptions.BlockerAlreadyBlockedException
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockage
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCatalog
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageCatalogable
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageThreshold
import org.neptrueworks.xenohermes.domain.social.blockage.params.isBlocked
import org.neptrueworks.xenohermes.domain.social.blockage.params.isMaximumBlockageExceeds
import org.neptrueworks.xenohermes.domain.social.blockage.params.isNotBlocked

public abstract class SocialBlockageEstablishingAggregateRoot: AggregateRoot(), SocialBlockageAggregatable {
    public abstract override val blocker: SocialBlockageBlocker
    public abstract override val blockageThreshold: SocialBlockageThreshold
}
