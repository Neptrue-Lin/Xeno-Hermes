package org.neptrueworks.xenohermes.domain.common.event

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.*
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES_EXCLUDING_COMMON
import withCompanionModifier

public final class DomainEventPatternLinter {
    @Test
    public final fun `Implementable Scope - domain event should NOT reside outside domain`() {
        Konsist.scopeFromProject().classesAndInterfacesAndObjects()
            .withoutModule(DOMAIN_MODULES)
            .assertFalse { it.hasParentInterfaceOf(DomainEvent::class, indirectParents = true) }
    }
    
    @Test
    public final fun `Implementable Scope - domain event should reside in package 'events' in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndInterfacesAndObjects()
            .withParentInterfaceOf(DomainEvent::class, indirectParents = true)
            .assertTrue { it.resideInPackage("org.neptrueworks.xenohermes.domain..events") }
    }

    @Test
    public final fun `Naming - named ending with 'Event' should be domain event in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndInterfacesAndObjects()
            .withNameEndingWith("Event")
            .assertTrue { it.hasParentOf(DomainEvent::class, indirectParents = true) }
    }
    
    @Test
    public final fun `Naming - domain event should be named ending with 'Event' in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndInterfacesAndObjects()
            .withParentOf(DomainEvent::class, indirectParents = true)
            .assertTrue { it.hasNameEndingWith("Event") }
    }

    @Test
    public final fun `Class or Object Type - domain event class or object should be data`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndObjects()
            .withParentInterfaceOf(DomainEvent::class, indirectParents = true)
            .assertTrue { it.hasDataModifier }
    }

    @Test
    public final fun `Interface Type - domain event interface should be marker`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).interfaces()
            .withParentInterfaceOf(DomainEvent::class, indirectParents = true)
            .assertTrue { it.functions().isEmpty() && it.properties().isEmpty() }
    }

    @Test
    public final fun `Constructor Visibility - constructor of domain event should be private`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentInterfaceOf(DomainEvent::class, indirectParents = true)
            .constructors
            .assertTrue { it.hasPrivateModifier }
    }

    @Test
    public final fun `Initializer Visibility - initializer of domain event should be internal`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentInterfaceOf(DomainEvent::class, indirectParents = true)
            .objects(includeNested = true)
            .withCompanionModifier()
            .assertTrue { it.hasInternalModifier }
    }

    @Test
    public final fun `Initialize Method Naming - name of method initialize of domain event should be 'initialize'`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentInterfaceOf(DomainEvent::class, indirectParents = true)
            .objects(includeNested = true)
            .withCompanionModifier()
            .functions()
            .assertTrue { it.name == "initialize" }
    }

    @Test
    public final fun `Initialize Method Visibility - method 'initialize' of domain event should be internal`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentInterfaceOf(DomainEvent::class, indirectParents = true)
            .objects(includeNested = true)
            .withCompanionModifier()
            .functions()
            .assertTrue { it.hasInternalModifier }
    }

    @Test
    public final fun `Initializer Method Number - number of method of domain event initializer should be only 1`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentInterfaceOf(DomainEvent::class, indirectParents = true)
            .objects(includeNested = true)
            .withCompanionModifier()
            .assertTrue { it.functions().count() == 1 }
    }

    @Test
    public final fun `Initialize Method Parameter Immutability - method parameter of domain event initializer should be immutable`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentInterfaceOf(DomainEvent::class, indirectParents = true)
            .objects(includeNested = true)
            .withCompanionModifier()
            .functions().parameters
            .assertFalse { it.type.isMutableType }
    }
}