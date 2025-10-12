package org.neptrueworks.xenohermes.contract.social.invitation

import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.gt
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationAggregatable
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationResponseAggregateRoot
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationIdentifier
import org.neptrueworks.xenohermes.domain.social.invitation.SocialInvitationResponseRepositable
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAcceptanceStatus
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationAudience
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationExpiryPeriod
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationIssuer
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationRevocationStatus
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
internal final class SocialInvitationResponseRepository(
    private val jimmerClient: KSqlClient
) : SocialInvitationResponseRepositable {
    override fun fetchByIdentifier(invitationId: SocialInvitationIdentifier): SocialInvitationResponseAggregateRoot {
        return this.jimmerClient.findById(SocialInvitation::class, invitationId)
            .run(::SocialInvitationResponseAggregator);
    }

    override fun fetchPrevious(issuer: SocialInvitationIssuer, invitee: SocialInvitationAudience): SocialInvitationAggregatable{
        return this.jimmerClient.createQuery(SocialInvitation::class) {
            where(table.issuer eq issuer)
            where(table.audience eq invitee)
            orderBy(table.issueDateTime.desc())
            select(table)
        }.fetchFirst()
    }

    override fun fetchAllPending(issuer: SocialInvitationIssuer): Iterable<SocialInvitationAggregatable> {
        return this.jimmerClient.createQuery(SocialInvitation::class) {
            where(table.issuer eq issuer)
            where(table.acceptanceStatus eq SocialInvitationAcceptanceStatus.NOT_ACCEPTED)
            where(table.revocationStatus eq SocialInvitationRevocationStatus.ENDURING)
            where(table.expiryPeriod gt SocialInvitationExpiryPeriod.TemporaryUnexpired(LocalDateTime.now()))
//            orderBy(table.invitationId.desc())
            select(table)
        }.execute()
    }

    override fun reposit(aggregateRoot: SocialInvitationResponseAggregateRoot) {
        val aggregator = aggregateRoot as SocialInvitationResponseAggregator;
        this.jimmerClient.saveCommand(aggregator.__resolve() as SocialInvitation, SaveMode.UPDATE_ONLY).execute();
    }
}