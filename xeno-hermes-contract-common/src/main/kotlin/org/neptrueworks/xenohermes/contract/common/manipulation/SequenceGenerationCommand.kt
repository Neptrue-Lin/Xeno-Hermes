package org.neptrueworks.xenohermes.contract.common.manipulation

import org.babyfish.jimmer.sql.kt.ast.KExecutable
import org.babyfish.jimmer.sql.runtime.JSqlClientImplementor
import java.sql.Connection
import kotlin.use

public final class SequenceGenerationCommand(
    private val jimmerClient: JSqlClientImplementor,
    private val sequenceName: String
) : KExecutable<Long> {
    public override fun execute(con: Connection?): Long {
        val nextVal = this.jimmerClient.dialect.getSelectIdFromSequenceSql(this.sequenceName);
        return this.jimmerClient.connectionManager.execute { connection -> 
            connection.prepareStatement(nextVal)
                .executeQuery().use { it.next(); it.getLong(1); }
        }
    }
}