import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    kotlin("jvm") version "2.2.0"
//    scala
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
//    apply(plugin = "scala")
    
    java {
        toolchain { languageVersion = JavaLanguageVersion.of(21) }
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.add("-Xwarning-level=NOTHING_TO_INLINE:disabled")
            freeCompilerArgs.add("-Xconsistent-data-class-copy-visibility")
            freeCompilerArgs.add("-Xnested-type-aliases")
        }
    }

//    tasks.withType<ScalaCompile>().configureEach {
//        scalaCompileOptions.additionalParameters = listOf(
//            "-Yexplicit-nulls",
//            "-language:strictEquality",
//            "-language:implicitConversions"
//        )
//    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}