package org.neptrueworks.xenohermes.domain.social.engagement.params

public data class SocialEngagementCount(val currentEngagement: Int) {
    public companion object {
        public val INITIAL get() = SocialEngagementCount(0);
    }
    internal final operator fun inc() = SocialEngagementCount(this.currentEngagement + 1)
    
    internal final operator fun dec() = SocialEngagementCount(this.currentEngagement - 1)
}