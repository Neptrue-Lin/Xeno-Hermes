plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.xenoHermesDomainCommon)
    implementation(libs.spring.boot.starter)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.junit.jupiter)
    testImplementation(libs.konsist)
    ksp(projects.xenoHermesContractCommon)
}