import org.jetbrains.kotlin.ir.backend.js.transformers.irToJs.argumentsWithVarargAsSingleArray

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    application
}

group = "dev.schlaubi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

application {
    mainClass = "dev.schlaubi.telegram.deppgpt.BotKt"
    applicationDefaultJvmArgs = listOf("--enable-preview")
}

dependencies {
    implementation(libs.telegram)
    implementation(libs.envconf)
    implementation(libs.mongodb.driver.kotlin.coroutine)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.logback.classic)
}

kotlin {
    jvmToolchain(20)

    compilerOptions {
        freeCompilerArgs.add("-Xcontext-receivers")
    }
}
