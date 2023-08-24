package dev.schlaubi.telegram.deppgpt.database

import com.mongodb.kotlin.client.coroutine.MongoClient
import dev.schlaubi.telegram.deppgpt.config.Config

class Database {
    private val mongoClient = MongoClient
        .create(Config.MONGO_URL)
    private val database = mongoClient.getDatabase(Config.MONGO_DATABASE)
    val threads = database.getCollection<GptThread>("threads")
}
