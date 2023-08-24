package dev.schlaubi.telegram.deppgpt.config

import dev.schlaubi.envconf.Config as EnvironmentConfig

object Config : EnvironmentConfig() {
    val TELEGRAM_API_KEY by this
    val MONGO_URL by this
    val MONGO_DATABASE by this
    val USE_WEBHOOK by getEnv(false, String::toBooleanStrict)
    val HOSTNAME by this
    val PORT by getEnv(8080, transform = String::toInt)
}
