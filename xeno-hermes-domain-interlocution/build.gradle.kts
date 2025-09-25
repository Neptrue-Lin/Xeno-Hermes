plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.xenoHermesDomainCommon)
    implementation(projects.xenoHermesDomainSocial)
    implementation(libs.spring.boot.starter)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.konsist)
    ksp(projects.xenoHermesContractCommon)
}