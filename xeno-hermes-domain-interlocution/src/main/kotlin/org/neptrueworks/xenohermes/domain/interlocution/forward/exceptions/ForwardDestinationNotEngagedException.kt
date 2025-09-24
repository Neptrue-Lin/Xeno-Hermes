package org.neptrueworks.xenohermes.domain.interlocution.forward.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageCorrespondenceException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForwarder
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDestination

public final class ForwardDestinationNotEngagedException internal constructor(
    val forwarder: MessageForwarder,
    val destination: ForwardMessageDestination,
) : MessageCorrespondenceException()