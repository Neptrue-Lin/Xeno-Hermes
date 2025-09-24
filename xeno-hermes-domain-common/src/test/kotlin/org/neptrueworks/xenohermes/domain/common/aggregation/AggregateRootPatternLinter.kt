package org.neptrueworks.xenohermes.domain.common.aggregation

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.*
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_COMMON_MODULE
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES_EXCLUDING_COMMON

public final class AggregateRootPatternLinter {
    @Test
    public final fun `Declarable Scope - aggregate root should NOT declare outside domain`(){
        Konsist.scopeFromProject().classesAndInterfacesAndObjects()
            .withoutModule(DOMAIN_MODULES)
            .assertFalse { it.hasParentInterfaceOf(AggregateRoot::class) }
    }
    
    @Test
    public final fun `Naming - every aggregate root should be named ending with 'AggregateRoot' in any modules`() {
        Konsist.scopeFromProject().classes()
            .withoutModule(DOMAIN_COMMON_MODULE)
            .withNameEndingWith("AggregateRoot")
            .assertTrue { it.hasParentOf(AggregateRoot::class) }
    }

    @Test
    public final fun `Naming - name ending with 'AggregateRoot' should be aggregate root in any modules`() {
        Konsist.scopeFromProject().classes()
            .withoutModule(DOMAIN_COMMON_MODULE)
            .withParentOf(AggregateRoot::class)
            .assertTrue { it.hasNameEndingWith("AggregateRoot") }
    }

    @Test
    public final fun `Type - aggregate root should NOT implement 'Aggregatable' directly`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentOf(AggregateRoot::class)
            .assertFalse { it.hasParentInterfaceOf(Aggregatable::class) }
    }

    @Test
    public final fun `Type - aggregate root should be abstract`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentOf(AggregateRoot::class)
            .assertTrue { it.hasAbstractModifier }
    }
    
    @Test
    public final fun `Mutatable Method Visibility - mutatable method should be internal`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentOf(AggregateRoot::class)
            .functions().withoutReturnValue()
            .assertTrue { it.hasInternalModifier }
    }

    @Test
    public final fun `Method Overridability - mutatable method should be final`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentOf(AggregateRoot::class)
            .functions()
            .assertTrue { it.hasFinalModifier }
    }
    
    @Test
    public final fun `Immutable Method Return Type - immutable method should not return mutable type`() {
        Konsist.scopeFromProject().classes()
            .withParentOf(AggregateRoot::class)
            .functions().returnTypes
            .assertFalse { it.isMutableType }
    }
    
    @Test
    public final fun `Mutable Property Setter Visibility - mutable property setter should be protected`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentOf(AggregateRoot::class)
            .properties().setters
            .assertTrue { it.hasProtectedModifier }
    }

    @Test
    public final fun `Mutable Method Parameter Immutability - mutable method parameter should be immutable`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentInterfaceOf(AggregateRoot::class)
            .functions().parameters.types
            .assertTrue { it.isMutableType }
    }
    
    @Test
    public final fun `Constructor Definition - aggregate root should not define any constructors`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentOf(AggregateRoot::class)
            .assertTrue { it.constructors.isEmpty() }
    }
}