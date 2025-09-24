plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(projects.xenoHermesDomainCommon)
    implementation(projects.xenoHermesDomainSocial)
//    implementation(projects.xenoHermesDataSocial)
    implementation(platform(libs.spring.boot.dependencies))
    implementation(libs.spring.boot.starter)
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-rsocket")
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.security)
    annotationProcessor(libs.spring.boot.configurationProcessor)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.junit.jupiter)
}