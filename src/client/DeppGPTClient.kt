package dev.schlaubi.telegram.deppgpt.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

private const val baseUrl = "https://europe-west1-deppgpt.cloudfunctions.net/DeppGPTRelease221"

@Serializable
data class Message(val role: Role, val content: String) {
    @Serializable
    enum class Role {
        @SerialName("assistant")
        ASSISTANT,

        @SerialName("user")
        USER
    }
}

@Serializable
data class MessageRequest(val maxMessages: Int, val messages: List<Message>)

@Serializable
data class MessageResponse(val answer: String, val totalTokens: Int)

object GptClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url.takeFrom(baseUrl)
        }
    }

    suspend fun requestMessages(input: MessageRequest) = client.post {
        contentType(ContentType.Application.Json)
        setBody(input)
    }.body<MessageResponse>()

    suspend fun requestMessage(prompt: String) =
        requestMessages(MessageRequest(4, listOf(Message(Message.Role.USER, prompt)))).answer
}
