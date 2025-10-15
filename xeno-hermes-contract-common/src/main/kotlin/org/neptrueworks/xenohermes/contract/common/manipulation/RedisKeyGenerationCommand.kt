package org.neptrueworks.xenohermes.contract.common.manipulation

import org.redisson.api.RedissonClient

public final class RedisKeyGenerationCommand(
    private val redissonClient: RedissonClient,
    private val key: String,
) {
    public fun execute(): Long {
        return this.redissonClient.getAtomicLong(this.key).incrementAndGet();
    }
}