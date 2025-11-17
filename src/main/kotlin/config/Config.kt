package dev.schlaubi.telegram.deppgpt.config

import dev.schlaubi.envconf.Config as EnvironmentConfig

object Config : EnvironmentConfig() {
    val TELEGRAM_API_KEY by environment
    val MONGO_URL by environment
    val MONGO_USERNAME by environment.optional()
    val MONGO_PASSWORD by environment.optional()
    val MONGO_AUTH_DATABASE by environment.optional()
    val MONGO_DATABASE by environment
    val USE_WEBHOOK by getEnv(false, String::toBooleanStrict)
    val HOSTNAME by environment
    val PORT by getEnv(8080, transform = String::toInt)
}
