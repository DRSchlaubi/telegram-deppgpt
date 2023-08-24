package dev.schlaubi.telegram.deppgpt.database

import dev.schlaubi.telegram.deppgpt.client.GptClient
import dev.schlaubi.telegram.deppgpt.client.models.Message
import dev.schlaubi.telegram.deppgpt.client.models.MessageRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GptThread(
    @SerialName("_id")
    val channelId: Long,
    val messages: List<Message>
) {
    suspend fun requestAnswer(input: String): GptThread {
        val request = Message(Message.Role.USER, input)
        val (answer) = GptClient.requestMessages(MessageRequest(4, messages + request))
        val allMessages = messages + request + Message(Message.Role.ASSISTANT, answer)

        return copy(messages = allMessages)
    }
}
