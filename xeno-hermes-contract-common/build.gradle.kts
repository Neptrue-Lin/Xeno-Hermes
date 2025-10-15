plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.xenoHermesDomainCommon)
    implementation(libs.kotlin.stdlib)
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.context.support)
    implementation(libs.ksp)
//    implementation(libs.spring.boot.starter.data.elasticsearch)
//    implementation(libs.spring.boot.starter.data.redis)
//    implementation(libs.spring.rabbit.stream)
//    implementation(libs.postgresql)
    implementation(libs.jimmer.spring.boot.starter)
    implementation(libs.jimmer.sql.kotlin)
    implementation(libs.redisson.spring.boot.starter)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.junit.jupiter)
}