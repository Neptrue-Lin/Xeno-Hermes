package org.neptrueworks.xenohermes.domain.common

import org.springframework.context.annotation.Configuration

@Configuration
public open class DomainRegistry {
    companion object {
        @JvmStatic
        public final val DOMAIN_COMMON_MODULE = "xeno-hermes-domain-common"
        @JvmStatic
        public final val DOMAIN_INTERLOCUTION_MODULE = "xeno-hermes-domain-interlocution"
        @JvmStatic
        public final val DOMAIN_SOCIAL_MODULE = "xeno-hermes-domain-social"

        @JvmStatic
        public final val DOMAIN_MODULES = listOf(
            DOMAIN_COMMON_MODULE,
            DOMAIN_INTERLOCUTION_MODULE,
            DOMAIN_SOCIAL_MODULE,
        )
        
        @JvmStatic
        public final val DOMAIN_MODULES_EXCLUDING_COMMON = listOf(
            DOMAIN_INTERLOCUTION_MODULE,
            DOMAIN_SOCIAL_MODULE,
        )
    }
}