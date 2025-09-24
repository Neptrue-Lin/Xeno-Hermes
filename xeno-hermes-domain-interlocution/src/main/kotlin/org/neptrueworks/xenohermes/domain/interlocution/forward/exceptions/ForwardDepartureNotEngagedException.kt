package org.neptrueworks.xenohermes.domain.interlocution.forward.exceptions

import org.neptrueworks.xenohermes.domain.interlocution.correspondence.exceptions.MessageCorrespondenceException
import org.neptrueworks.xenohermes.domain.interlocution.correspondence.params.MessageForwarder
import org.neptrueworks.xenohermes.domain.interlocution.forward.params.ForwardMessageDeparture

public final class ForwardDepartureNotEngagedException internal constructor(
    val forwarder: MessageForwarder,
    val departure: ForwardMessageDeparture,
) : MessageCorrespondenceException()