package org.neptrueworks.xenohermes.domain.interlocution.scheme


public interface MessageScheme {
    val schemeId: MessageSchemeIdentifier
    val unsendPermission: MessageUnsendPermission
    val quotationPermission: MessageQuotationPermission
    val reactionPermission: MessageReactionPermission
    val forwardPermission: MessageForwardPermission
    val mentionPermission: MessageMentionPermission
    val unsendWindow: MessageUnsendWindow
    val lengthThreshold: MessageLengthThreshold
}