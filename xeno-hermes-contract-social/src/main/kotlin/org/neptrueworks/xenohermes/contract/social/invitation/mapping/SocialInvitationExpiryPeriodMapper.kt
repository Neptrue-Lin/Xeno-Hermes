package org.neptrueworks.xenohermes.contract.social.invitation.mapping

import org.babyfish.jimmer.sql.runtime.ScalarProvider
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationExpiryPeriod
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
public final class SocialInvitationExpiryPeriodMapper : ScalarProvider<SocialInvitationExpiryPeriod, LocalDateTime> {
    public override fun toScalar(sqlValue: LocalDateTime) = when(sqlValue) {
        LocalDateTime.MAX -> SocialInvitationExpiryPeriod.NonExpiring
        else -> SocialInvitationExpiryPeriod.TemporaryUnexpired(sqlValue)
    }
    
    public override fun toSql(scalarValue: SocialInvitationExpiryPeriod) = when(scalarValue) {
        is SocialInvitationExpiryPeriod.NonExpiring -> LocalDateTime.MAX
        is SocialInvitationExpiryPeriod.TemporaryUnexpired -> scalarValue.expiredAt
    }
}