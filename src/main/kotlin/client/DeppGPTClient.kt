package dev.schlaubi.telegram.deppgpt.client

import dev.schlaubi.telegram.deppgpt.client.models.Message
import dev.schlaubi.telegram.deppgpt.client.models.MessageRequest
import dev.schlaubi.telegram.deppgpt.client.models.MessageResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

private const val baseUrl = "https://europe-west1-deppgpt.cloudfunctions.net/DeppGPTRelease221"

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
