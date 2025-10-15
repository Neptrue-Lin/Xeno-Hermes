package org.neptrueworks.xenohermes.contract.common.manipulation

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.runtime.JSqlClientImplementor
import org.springframework.stereotype.Component

@Component
public final class SequenceGenerator(
    private val jimmerClient: KSqlClient,
) {
    public fun nextValueCommand(sequenceName: String) = 
        SequenceGenerationCommand(this.jimmerClient.javaClient, sequenceName);
}