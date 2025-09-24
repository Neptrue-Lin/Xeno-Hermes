package org.neptrueworks.xenohermes.domain.common.aggregation

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.*
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES_EXCLUDING_COMMON

public final class AggregateRepositoryPatternLinter {
    @Test
    public final fun `Declarable Scope - aggregate repository should NOT declare outside domain`() {
        Konsist.scopeFromProject().interfaces()
            .withoutModule(DOMAIN_MODULES)
            .assertFalse { it.hasParentInterfaceOf(AggregateRepositable::class) }
    }

    @Test
    public final fun `Implementable Scope - aggregate repository should NOT implement in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES).classesAndObjects()
            .assertFalse { it.hasParentInterfaceOf(AggregateRepositable::class, indirectParents = true) }
    }
    
    @Test
    public final fun `Fetch Method Parameter Immutability - fetch method parameters should be immutable`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).interfaces()
            .withParentInterfaceOf(AggregateRepositable::class)
            .functions().parameters.types
            .assertFalse { it.isMutableType }
    }

    @Test
    public final fun `Fetch Method Return Type - fetch method returning class should return 'AggregateRoot'`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).interfaces()
            .withParentInterfaceOf(AggregateRepositable::class)
            .functions().returnTypes.withoutGeneric().sourceDeclarations()
            .assertTrue {
                it.asClassOrInterfaceDeclaration()!!.hasParentInterfaceOf(Aggregatable::class, indirectParents = true)
            }
    }

    @Test
    public final fun `Fetch Method Return Type - fetch method returning collection should return 'Aggregatable'`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).interfaces()
            .withParentInterfaceOf(AggregateRepositable::class)
            .functions().returnTypes
            .withGeneric().typeArguments
            .assertTrue { it.sourceDeclaration!!.hasClassOrInterfaceDeclaration { 
                it.hasParentInterfaceOf(Aggregatable::class, indirectParents = true) 
            } }
    }
}