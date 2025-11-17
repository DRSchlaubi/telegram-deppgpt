package dev.schlaubi.telegram.deppgpt.database

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.kotlin.client.coroutine.MongoClient
import dev.schlaubi.telegram.deppgpt.config.Config

private val Config.DB_CONNECTION_SETTINGS get() = MongoClientSettings.builder()
    .applyConnectionString(ConnectionString(MONGO_URL))
    .apply {
        val username = MONGO_USERNAME ?: return@apply
        val password = MONGO_PASSWORD ?: return@apply
        val authDatabase = MONGO_AUTH_DATABASE ?: return@apply

        credential(MongoCredential.createCredential(username, authDatabase, password.toCharArray()))
    }
    .build()

class Database {
    private val mongoClient = MongoClient.create(Config.DB_CONNECTION_SETTINGS)
    private val database = mongoClient.getDatabase(Config.MONGO_DATABASE)
    val threads = database.getCollection<GptThread>("threads")
}
