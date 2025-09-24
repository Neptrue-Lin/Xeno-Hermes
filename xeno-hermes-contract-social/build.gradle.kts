plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.xenoHermesContractCommon)
    implementation(projects.xenoHermesDomainCommon)
    implementation(projects.xenoHermesDomainSocial)
    implementation(libs.spring.boot.starter)
//    implementation(libs.spring.boot.starter.data.neo4j)
    implementation(libs.spring.boot.starter.data.elasticsearch)
    implementation(libs.spring.boot.starter.data.redis)
    implementation(libs.spring.rabbit.stream)
    implementation(libs.postgresql)
    implementation(libs.jimmer.spring.boot.starter)
    implementation(libs.jimmer.sql.kotlin)
    ksp(libs.jimmer.ksp)
    ksp(projects.xenoHermesContractCommon)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.junit.jupiter)
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
