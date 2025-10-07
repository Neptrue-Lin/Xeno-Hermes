package org.neptrueworks.xenohermes.domain.interlocution.reaction.params

public sealed class MessageReactionStatus {
    public data object NotReacted : MessageReactionStatus()
    public data class Reacted(val type: MessageReactionType) : MessageReactionStatus()
}

public inline fun MessageReactionStatus.isReacted() = when (this) {
    is MessageReactionStatus.Reacted -> true
    is MessageReactionStatus.NotReacted -> false
}

public inline fun MessageReactionStatus.isNotReacted() = !this.isReacted();
