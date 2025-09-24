package org.neptrueworks.xenohermes.domain.social.blockage.exceptions

import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlockee
import org.neptrueworks.xenohermes.domain.social.blockage.params.SocialBlockageBlocker


public final class BlockerAlreadyBlockedException internal constructor(
    val blocker: SocialBlockageBlocker,
    val blockee: SocialBlockageBlockee
) : SocialBlockageException()
