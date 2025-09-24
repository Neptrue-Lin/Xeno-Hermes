plugins {
    kotlin("jvm")
}

group = "org.neptrueworks.xenohermes"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}