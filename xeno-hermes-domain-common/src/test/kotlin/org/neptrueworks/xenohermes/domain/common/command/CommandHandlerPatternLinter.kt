package org.neptrueworks.xenohermes.domain.common.command

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParentInterfaceOf
import com.lemonappdev.konsist.api.ext.list.withParentOf
import com.lemonappdev.konsist.api.ext.list.withoutModule
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_COMMON_MODULE
import org.neptrueworks.xenohermes.domain.common.DomainRegistry.Companion.DOMAIN_MODULES_EXCLUDING_COMMON

public final class CommandHandlerPatternLinter {
    @Test
    public final fun `Implementable Scope - command handler should NOT reside outside domain`() {
        Konsist.scopeFromProject().classesAndObjects()
            .withoutModule(DOMAIN_COMMON_MODULE)
            .assertFalse { it.hasParentInterfaceOf(CommandHandler::class) }
    }
    
    @Test
    public final fun `Implementable Scope - command handler should reside in package 'commands' in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndObjects()
            .withParentInterfaceOf(CommandHandler::class)
            .assertTrue { it.resideInPackage("org.neptrueworks.xenohermes.domain..commands") }
    }

    @Test
    public final fun `Naming - named ending with 'CommandHandler' should be command handler in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndObjects()
            .withNameEndingWith("CommandHandler")
            .assertTrue { it.hasParentClassOf(CommandHandler::class) }
    }

    @Test
    public final fun `Naming - command handler should be named ending with 'CommandHandler' in domain`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classesAndObjects()
            .withParentOf(CommandHandler::class)
            .assertTrue { it.hasNameEndingWith("CommandHandler") }
    }

    @Test
    public final fun `Inheritability - command handler should be final`() {
        Konsist.scopeFromModules(DOMAIN_MODULES_EXCLUDING_COMMON).classes()
            .withParentInterfaceOf(CommandHandler::class)
            .assertTrue { it.hasFinalModifier }
    }
}