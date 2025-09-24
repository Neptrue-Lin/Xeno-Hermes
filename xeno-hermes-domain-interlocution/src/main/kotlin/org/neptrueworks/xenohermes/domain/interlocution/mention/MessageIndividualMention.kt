package org.neptrueworks.xenohermes.domain.interlocution.mention

public sealed class MessageIndividualMention {
    public data object NotMentioned : MessageIndividualMention();
    public data class Mentioned(val individuals: Collection<MessageMentionIdentifier.IndividualIdentifier>) : MessageIndividualMention();
}

public inline fun MessageIndividualMention.isMentioned() = when (this) {
    is MessageIndividualMention.NotMentioned -> false
    is MessageIndividualMention.Mentioned -> true
}

public inline fun MessageIndividualMention.isNotMentioned() = !this.isMentioned();