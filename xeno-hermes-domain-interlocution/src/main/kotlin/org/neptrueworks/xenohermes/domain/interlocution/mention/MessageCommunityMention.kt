package org.neptrueworks.xenohermes.domain.interlocution.mention


public sealed class MessageCommunityMention {
    public data object NotMentioned : MessageCommunityMention();
    public data object Mentioned : MessageCommunityMention();
}

public inline fun MessageCommunityMention.isMentioned() = when(this) {
    is MessageCommunityMention.NotMentioned -> false;
    is MessageCommunityMention.Mentioned -> true;
}

public inline fun MessageCommunityMention.isNotMentioned() = !this.isMentioned();