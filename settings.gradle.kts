rootProject.name = "xeno-hermes"

include(
    "xeno-hermes-domain-common",
    "xeno-hermes-domain-interlocution",
    "xeno-hermes-domain-social",
    "xeno-hermes-query-common",
    "xeno-hermes-query-interlocution",
    "xeno-hermes-query-social",
    "xeno-hermes-contract-common",
    "xeno-hermes-contract-interlocution",
    "xeno-hermes-contract-social",
    "xeno-hermes-gateway-common",
    "xeno-hermes-gateway-interlocution",
    "xeno-hermes-gateway-social",
    "xeno-hermes-presence",
    "xeno-hermes-delivery"
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        maven { setUrl("https://maven.aliyun.com/repository/public/") }
        maven { setUrl("https://maven.aliyun.com/repository/spring/") }
        maven { setUrl("https://maven.aliyun.com/repository/google/") }
        maven { setUrl("https://maven.aliyun.com/repository/spring-plugin/") }
        maven { setUrl("https://maven.aliyun.com/repository/grails-core/") }
        maven { setUrl("https://maven.aliyun.com/repository/apache-snapshots/") }
        maven { setUrl("https://repo.spring.io/milestone") }
//        maven { setUrl("https://repo.spring.io/snapshot") }
        mavenCentral()
    }
}