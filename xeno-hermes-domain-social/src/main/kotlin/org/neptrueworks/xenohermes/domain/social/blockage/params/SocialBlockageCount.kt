package org.neptrueworks.xenohermes.domain.social.blockage.params

public data class SocialBlockageCount(val currentBlockage: Int) {
    companion object {
        public val INITIAL = SocialBlockageCount(0)
    }
    internal final operator fun inc() = SocialBlockageCount(this.currentBlockage + 1)
    internal final operator fun dec() = SocialBlockageCount(this.currentBlockage - 1)
}