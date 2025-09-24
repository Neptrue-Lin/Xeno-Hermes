package org.neptrueworks.xenohermes.domain.social.engagement.params

public data class SocialEngagementCount private constructor(val currentEngagement: UInt) {
    public companion object {
        public val INITIAL_ENGAGEMENT get() = SocialEngagementCount(0U);
    }
    internal final operator fun inc() = SocialEngagementCount(this.currentEngagement + 1U)
    
    internal final operator fun dec() = SocialEngagementCount(this.currentEngagement - 1U)
}