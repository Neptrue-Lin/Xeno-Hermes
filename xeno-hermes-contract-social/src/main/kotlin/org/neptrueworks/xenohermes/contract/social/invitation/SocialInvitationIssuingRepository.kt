package org.neptrueworks.xenohermes.contract.social.invitation

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.gt
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationAggregatable
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationResponseAggregateRoot
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIssuingAggregateRoot
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIssuingRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationResponseRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAcceptanceStatus
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAudience
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationExpiryPeriod
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationIssuer
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevocationStatus
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
internal final class SocialInvitationIssuingRepository(
    private val kSqlClient: KSqlClient
) : SocialInvitationIssuingRepositable {
    override fun reposit(aggregateRoot: SocialInvitationIssuingAggregateRoot) {
        val aggregator = aggregateRoot as SocialInvitationIssuingAggregator;
        this.kSqlClient.saveCommand(aggregator.resolve(), SaveMode.UPDATE_ONLY).execute();
    }
}