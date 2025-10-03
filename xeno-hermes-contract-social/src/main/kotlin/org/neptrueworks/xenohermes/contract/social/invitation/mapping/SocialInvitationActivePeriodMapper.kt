package org.neptrueworks.xenohermes.contract.social.invitation.mapping

import org.babyfish.jimmer.sql.runtime.ScalarProvider
import org.neptrueworks.xenohermes.domain.social.invitation.params.SocialInvitationActivePeriod
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
public final class SocialInvitationActivePeriodMapper : ScalarProvider<SocialInvitationActivePeriod, LocalDateTime> {
    public override fun toScalar(sqlValue: LocalDateTime) = when(sqlValue) {
        LocalDateTime.MIN -> SocialInvitationActivePeriod.PermanentPassive
        LocalDateTime.MAX -> SocialInvitationActivePeriod.PermanentActive
        else -> SocialInvitationActivePeriod.TemporaryActive(sqlValue)
    }
    
    public override fun toSql(scalarValue: SocialInvitationActivePeriod) = when(scalarValue) {
        is SocialInvitationActivePeriod.PermanentPassive -> LocalDateTime.MIN
        is SocialInvitationActivePeriod.PermanentActive -> LocalDateTime.MAX
        is SocialInvitationActivePeriod.TemporaryActive -> scalarValue.activatedAt
    }
}