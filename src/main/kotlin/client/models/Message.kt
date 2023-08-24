package dev.schlaubi.telegram.deppgpt.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
