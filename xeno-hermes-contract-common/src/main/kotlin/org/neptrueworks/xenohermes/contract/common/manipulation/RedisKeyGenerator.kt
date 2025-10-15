package org.neptrueworks.xenohermes.contract.common.manipulation

import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component

@Component
public final class RedisKeyGenerator(
    private val redissonClient: RedissonClient
) {
    public fun nextValueCommand(key: String) = 
        RedisKeyGenerationCommand(this.redissonClient, key);
}