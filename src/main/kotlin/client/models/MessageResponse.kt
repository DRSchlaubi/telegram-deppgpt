package dev.schlaubi.telegram.deppgpt.client.models

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(val answer: String, val totalTokens: Int)
