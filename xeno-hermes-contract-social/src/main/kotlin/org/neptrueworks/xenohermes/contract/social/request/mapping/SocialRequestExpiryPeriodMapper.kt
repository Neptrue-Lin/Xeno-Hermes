package org.neptrueworks.xenohermes.contract.social.request.mapping

import org.babyfish.jimmer.sql.runtime.ScalarProvider
import org.neptrueworks.xenohermes.domain.social.request.params.SocialRequestExpiryPeriod
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
public final class SocialRequestExpiryPeriodMapper : ScalarProvider<SocialRequestExpiryPeriod, LocalDateTime> {
    public override fun toScalar(sqlValue: LocalDateTime) = when(sqlValue) {
        LocalDateTime.MAX -> SocialRequestExpiryPeriod.NonExpiring
        else -> SocialRequestExpiryPeriod.TemporaryUnexpired(sqlValue)
    }
    
    public override fun toSql(scalarValue: SocialRequestExpiryPeriod) = when(scalarValue) {
        is SocialRequestExpiryPeriod.NonExpiring -> LocalDateTime.MAX
        is SocialRequestExpiryPeriod.TemporaryUnexpired -> scalarValue.expiryPeriod
    }
}