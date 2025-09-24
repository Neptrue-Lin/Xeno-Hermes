package org.neptrueworks.xenohermes.domain.common.aggregation

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutAbstractModifier
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParentOf
import com.lemonappdev.konsist.api.ext.list.withoutModule
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES_EXCLUDING_COMMON

public final class AggregatablePatternLinter {
    @Test
    public final fun `Declarable Scope - aggregatable should NOT declare outside domain`() {
        Konsist.scopeFromProject().interfaces()
            .withoutModule(DOMAIN_MODULES)
            .assertFalse { it.hasParentInterfaceOf(Aggregatable::class) }
    }
    
    @Test
    public final fun `Implementable Scope - aggregatable should NOT implement in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES).classes()
            .withoutAbstractModifier()
            .assertFalse { it.hasParentInterfaceOf(Aggregatable::class, indirectParents = true) }
    }
    
    @Test
    public final fun `Naming - named ending with 'Aggregatable' should not be aggregatable in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).interfaces()
            .withNameEndingWith("Aggregatable")
            .assertTrue { it.hasParentOf(Aggregatable::class) }
    }
    
    @Test
    public final fun `Naming - aggregatable should be named ending with 'Aggregatable' in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).interfaces()
            .withParentOf(Aggregatable::class)
            .assertTrue { it.hasNameEndingWith("Aggregatable") }
    }
}