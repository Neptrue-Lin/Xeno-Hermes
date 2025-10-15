package org.neptrueworks.xenohermes.contract.interlocution.moderation.mapping

import org.babyfish.jimmer.sql.runtime.ScalarProvider
import org.neptrueworks.xenohermes.contract.interlocution.moderation.params.InterlocutionBanIdentifier
import org.neptrueworks.xenohermes.domain.interlocution.moderation.params.InterlocutionBanActivePeriod
import java.time.LocalDateTime

public final class InterlocutionBanActivePeriodMapper : ScalarProvider<InterlocutionBanActivePeriod, LocalDateTime> {
    public override fun toScalar(sqlValue: LocalDateTime) = when (sqlValue) {
        LocalDateTime.MAX -> InterlocutionBanActivePeriod.Permanent;
        else              -> InterlocutionBanActivePeriod.Temporal(sqlValue);
    }

    public override fun toSql(scalarValue: InterlocutionBanActivePeriod) = when (scalarValue) {
        is InterlocutionBanActivePeriod.Permanent -> LocalDateTime.MAX;
        is InterlocutionBanActivePeriod.Temporal  -> scalarValue.banUntil;
    }
}