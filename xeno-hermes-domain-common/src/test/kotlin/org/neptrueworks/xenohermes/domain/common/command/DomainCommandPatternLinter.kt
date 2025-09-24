package org.neptrueworks.xenohermes.domain.common.command

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParentInterfaceOf
import com.lemonappdev.konsist.api.ext.list.withParentOf
import com.lemonappdev.konsist.api.ext.list.withoutModule
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES_EXCLUDING_COMMON

public final class DomainCommandPatternLinter {
    @Test
    public final fun `Implementable Scope - domain command should not reside outside domain`(){
        Konsist.scopeFromProject().classesAndInterfacesAndObjects()
            .withoutModule(DOMAIN_MODULES)
            .assertFalse { it.hasParentInterfaceOf(DomainCommand::class, indirectParents = true) }
    }
    
    @Test
    public final fun `Implementable Scope - domain command should reside in package 'commands' in domain`(){
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndInterfacesAndObjects()
            .withParentInterfaceOf(DomainCommand::class, indirectParents = true)
            .assertTrue { it.resideInPackage("org.neptrueworks.xenohermes.domain..commands") }
    }
    
    @Test
    public final fun `Naming - named ending with 'Command' should be taken by domain event in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndInterfacesAndObjects()
            .withNameEndingWith("Command")
            .assertTrue { it.hasParentOf(DomainCommand::class, indirectParents = true) }
    }
    
    @Test
    public final fun `Naming - domain command should be named ending with 'Command' in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndInterfacesAndObjects()
            .withParentOf(DomainCommand::class, indirectParents = true)
            .assertTrue { it.hasNameEndingWith("Command") }
    }
    
    @Test
    public final fun `Class or Object Type - domain command class or object should be data`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndObjects()
            .withParentInterfaceOf(DomainCommand::class, indirectParents = true)
            .assertTrue { it.hasDataModifier }
    }
    
    @Test
    public final fun `Interface Type - domain command interface should be marker`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).interfaces()
            .withParentInterfaceOf(DomainCommand::class, indirectParents = true)
            .assertTrue { it.functions().isEmpty() && it.properties().isEmpty() }
    }
}