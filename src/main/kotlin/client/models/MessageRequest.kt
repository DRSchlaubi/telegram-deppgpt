package dev.schlaubi.telegram.deppgpt.client.models

import kotlinx.serialization.Serializable

@Serializable
data class MessageRequest(val maxMessages: Int, val messages: List<Message>)
