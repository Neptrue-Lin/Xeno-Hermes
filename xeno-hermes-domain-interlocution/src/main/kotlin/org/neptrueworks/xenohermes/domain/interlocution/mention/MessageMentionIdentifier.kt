package org.neptrueworks.xenohermes.domain.interlocution.mention

public sealed class MessageMentionIdentifier {
    public data class IndividualIdentifier(val identifier: Long) : MessageMentionIdentifier();
    public data class CommunityIdentifier(val identifier: Long) : MessageMentionIdentifier();
//    public data class Role(val identifier: Long) : MessageMentionIdentifier();
//    public data class Hierarchy(val identifier: Long) : MessageMentionIdentifier();
}