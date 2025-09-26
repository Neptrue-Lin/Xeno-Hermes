package org.neptrueworks.xenohermes.contract.common.mapping

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import org.neptrueworks.xenohermes.domain.common.models.InlineClass
import java.io.File

public final class InlineClassMapperGenerator(
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {
    public override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation(InlineClass::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.classKind == ClassKind.CLASS }
            .forEach(::generateMapper)

        return emptyList()
    }

    private fun getTargetName(valueClassPackage: String): String {
        return if (valueClassPackage.contains("domain"))
            "contract"
        else if (valueClassPackage.contains("query"))
            "query"
        else if (valueClassPackage.contains("contract"))
            "contract"
        else throw Exception("Unknown target for package: $valueClassPackage");
    }
    
    private fun getModuleName(valueClassPackage: String): String {
        return if (valueClassPackage.contains("social"))
            "social"
        else if (valueClassPackage.contains("interlocution"))
            "interlocution" 
        else throw Exception("Unknown module for package: $valueClassPackage");
    }
    
    private fun generateMapper(valueClass: KSClassDeclaration) {
        val valueClassName = valueClass.simpleName.asString();
        val valueClassPackage = valueClass.packageName.asString();

        val property = valueClass.primaryConstructor?.parameters?.firstOrNull()!!;
        val propertyName = property.name?.getShortName()!!;
        val propertyType = property.type.resolve();
        val sqlTypeName = propertyType.declaration.simpleName.asString();
        val sqlTypePackage = propertyType.declaration.packageName.asString();

        val moduleName = this.getModuleName(valueClassPackage);
        val targetName = this.getTargetName(valueClassPackage);
        val packageName = "org.neptrueworks.xenohermes.${targetName}.${moduleName}.mapping";
       
        val rootDirectory = File(System.getProperty("user.dir")).parentFile;
        val file = File(
            "$rootDirectory/xeno-hermes-${targetName}-${moduleName}/build/generated/ksp/main/kotlin" +
            "/org/neptrueworks/xenohermes/${targetName}/${moduleName}/mapping/${valueClassName}Mapper.kt"
        )

        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (!file.exists())
            file.createNewFile();

        file.writeText(
            generateMapperCode(packageName, valueClassName, valueClassPackage, propertyName, sqlTypeName, sqlTypePackage)
        )
    }

    private fun generateMapperCode(
        packageName: String,
        valueClassName: String,
        valueClassPackage: String,
        propertyName: String,
        sqlTypeName: String,
        sqlTypePackage: String
    ) = """
    package $packageName
    
    import org.babyfish.jimmer.sql.runtime.ScalarProvider
    import org.springframework.stereotype.Component
    import ${valueClassPackage}.${valueClassName}
    import ${sqlTypePackage}.${sqlTypeName}
    
    @Component
    public final class ${valueClassName}Mapper : ScalarProvider<${valueClassName}, ${sqlTypeName}> {
        public override fun toScalar(sqlValue: $sqlTypeName)  = ${valueClassName}(sqlValue);
        public override fun toSql(scalarValue: $valueClassName) = scalarValue.${propertyName};
    }
    """.trimIndent()
}
