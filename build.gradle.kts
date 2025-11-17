plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "dev.schlaubi"

repositories {
    mavenCentral()
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
    implementation(libs.kotlin.logging)
}

application {
    mainClass = "dev.schlaubi.telegram.deppgpt.BotKt"
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_24
}
