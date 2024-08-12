package dev.schlaubi.telegram.deppgpt.core

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.api.send.withTypingAction
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onContentMessage
import dev.inmo.tgbotapi.types.chat.PreviewPrivateChat
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.schlaubi.telegram.deppgpt.Bot
import dev.schlaubi.telegram.deppgpt.client.GptClient
import dev.schlaubi.telegram.deppgpt.database.GptThread
import kotlinx.coroutines.flow.firstOrNull

context(Bot)
suspend fun BehaviourContext.handleMessages() = onContentMessage { message ->
    val isPrivate = message.chat is PreviewPrivateChat
    val text = (message.content as? TextContent)?.text
    if (text?.startsWith('/') == true) return@onContentMessage
    if (!isPrivate && text?.startsWith("@deppgpt") != true) return@onContentMessage
    withTypingAction(message.chat) {
        if (text.isNullOrBlank()) {
            reply(message, GptClient.requestMessage("Wieso kannst du nur Text verstehen?"))
            return@withTypingAction
        }

        val conversation = database.threads.find(Filters.eq("_id", message.chat.id.chatId.long)).firstOrNull()
        val newConversation = if (conversation == null) {
            val blankConversation = GptThread(message.chat.id.chatId.long, emptyList())
            blankConversation.requestAnswer(text).also { finalConversation ->
                database.threads.insertOne(finalConversation)
            }
        } else {
            conversation.requestAnswer(text).also { finalConversation ->
                database.threads.updateOne(
                    Filters.eq("_id", message.chat.id),
                    Updates.set("messages", finalConversation.messages)
                )
            }
        }

        val content = newConversation.messages.last().content
        if (isPrivate) {
            sendMessage(message.chat, content)
        } else {
            reply(message, content)
        }
    }
}
