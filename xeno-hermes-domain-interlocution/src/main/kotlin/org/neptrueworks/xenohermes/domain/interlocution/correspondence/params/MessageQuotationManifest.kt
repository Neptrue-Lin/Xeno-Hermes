package org.neptrueworks.xenohermes.domain.interlocution.correspondence.params

public sealed class MessageQuotationManifest {
    public data object NotQuoted : MessageQuotationManifest();
    public data class Quoted(val quotations: Collection<MessageQuotation>): MessageQuotationManifest();
}


public inline fun MessageQuotationManifest.isQuoted() = when(this) {
    is MessageQuotationManifest.NotQuoted -> false
    is MessageQuotationManifest.Quoted -> true
}
public inline fun MessageQuotationManifest.isNotQuoted() = !this.isQuoted();