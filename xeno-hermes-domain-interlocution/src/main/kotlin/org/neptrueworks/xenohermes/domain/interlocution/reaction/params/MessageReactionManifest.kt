package org.neptrueworks.xenohermes.domain.interlocution.reaction.params

public abstract class MessageReactionManifest {
    public abstract fun isReacted(reactor: MessageReactionReactor) : Boolean;
    public final inline fun isNotReacted(reactor: MessageReactionReactor) = !this.isReacted(reactor);
    
    public abstract fun react();
}
