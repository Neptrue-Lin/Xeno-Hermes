package org.neptrueworks.xenohermes.domain.interlocution.moderation.params

public enum class InterlocutionBanPrivilege {
    FORBIDDEN,
    PERMITTED
}

public inline fun InterlocutionBanPrivilege.isForbidden() =
    this == InterlocutionBanPrivilege.FORBIDDEN

public inline fun InterlocutionBanPrivilege.isPermitted() = 
    this == InterlocutionBanPrivilege.PERMITTED