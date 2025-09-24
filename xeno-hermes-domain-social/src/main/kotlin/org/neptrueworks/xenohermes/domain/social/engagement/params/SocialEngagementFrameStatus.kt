package org.neptrueworks.xenohermes.domain.social.engagement.params

public enum class SocialEngagementFrameStatus {
    FRAMED,
    NOT_FRAMED;
}

public inline fun SocialEngagementFrameStatus.isFramed() = 
    this == SocialEngagementFrameStatus.FRAMED;
public inline fun SocialEngagementFrameStatus.isNotFramed() = 
    this == SocialEngagementFrameStatus.NOT_FRAMED;
