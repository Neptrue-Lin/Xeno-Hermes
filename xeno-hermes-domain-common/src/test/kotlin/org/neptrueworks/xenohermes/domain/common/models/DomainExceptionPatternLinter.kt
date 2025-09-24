package org.neptrueworks.xenohermes.domain.common.models

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.*
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withAbstractModifier
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutAbstractModifier
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES_EXCLUDING_COMMON

public final class DomainExceptionPatternLinter {
    @Test
    public final fun `Implementable Scope - domain exception should not reside outside domain`(){
        Konsist.scopeFromProject().classesAndInterfacesAndObjects()
            .withoutModule(DOMAIN_MODULES)
            .assertFalse { it.hasParentInterfaceOf(DomainException::class, indirectParents = true) }
    }

    @Test
    public final fun `Implementable Scope - domain exception should reside in package 'exceptions' in domain`(){
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndInterfacesAndObjects()
            .withParentInterfaceOf(DomainException::class, indirectParents = true)
            .assertTrue { it.resideInPackage("org.neptrueworks.xenohermes.domain..exceptions") }
    }
    
    @Test
    public final fun `Concrete Exception Inheritability - concrete exception should be final`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withoutAbstractModifier()
            .withParentOf(DomainException::class, indirectParents = true)
            .assertTrue { it.hasFinalModifier }
    }
    
    @Test
    public final fun `Concrete Exception Property Immutability - property of concrete exception should be immutable`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withoutAbstractModifier()
            .withParentOf(DomainException::class, indirectParents = true)
            .properties()
            .assertFalse { it.hasSetter }
    }
    
    @Test
    public final fun `Marker Exception - abstract exception should be marker`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withAbstractModifier()
            .withParentOf(DomainException::class, indirectParents = true)
            .assertTrue { it.functions().isEmpty() && it.properties().isEmpty() }
    }
    
    @Test
    public final fun `Constructor Visibility - constructor should be internal`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentOf(DomainException::class, indirectParents = true)
            .constructors
            .assertTrue { it.hasInternalModifier }
    }
    
    @Test
    public final fun `Property Readonly - property should be val`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentOf(DomainException::class, indirectParents = true)
            .properties()
            .assertTrue { it.isVal }
    }

    @Test
    public final fun `Message Property Overridability - property 'message' should not be overridden`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentOf(DomainException::class, indirectParents = true)
            .properties().withName("message")
            .assertFalse { it.hasOverrideModifier }
    }
    
    @Test
    public final fun `Property Field Immutability - property field should be immutable`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentOf(DomainException::class, indirectParents = true)
            .properties()
            .assertFalse { it.type!!.isMutableType }
    }

//    @Test
//    public final fun `Throwability - domain exception should be thrown in aggregate only`() {
//        for (module in DOMAIN_MODULES_EXCLUDING_COMMON) {
//            Konsist.scopeFromModule(module).packages
//        }
//    }
}