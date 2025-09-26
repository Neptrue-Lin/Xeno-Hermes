package org.neptrueworks.xenohermes.contract.common.mapping

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import org.springframework.stereotype.Component

@Component
public final class InlineClassMapperGeneratorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return InlineClassMapperGenerator(environment.codeGenerator)
    }
}