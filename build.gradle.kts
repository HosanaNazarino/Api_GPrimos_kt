plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "2.0.0"
    application
}

application {
    mainClass.set("org.example.MainKt")
}


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation ("io.ktor:ktor-server-core:2.3.12")
    implementation ("io.ktor:ktor-server-netty:2.3.12")
    implementation ("io.ktor:ktor-serialization-kotlinx-json:2.3.12")
    implementation ("io.ktor:ktor-server-content-negotiation:2.3.12")

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    implementation ("ch.qos.logback:logback-classic:1.2.6")

    testImplementation ("org.jetbrains.kotlin:kotlin-test-junit:1.8.0")
    testImplementation ("io.ktor:ktor-server-tests:2.3.12")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}