package org.neptrueworks.xenohermes.domain.social.blockage.params

public data class SocialBlockageCount private constructor(val currentBlockage: UInt) {
    public companion object {
        public val INITIAL_BLOCKAGE get() = SocialBlockageCount(0U);
    }
    internal final operator fun inc() = SocialBlockageCount(this.currentBlockage + 1U)
    
    internal final operator fun dec() = SocialBlockageCount(this.currentBlockage - 1U)
}