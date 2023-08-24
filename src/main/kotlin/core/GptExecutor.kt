package dev.schlaubi.telegram.deppgpt.core

import com.github.kotlintelegrambot.dispatcher.handlers.TextHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatAction
import com.github.kotlintelegrambot.entities.ChatId
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import dev.schlaubi.telegram.deppgpt.Bot
import dev.schlaubi.telegram.deppgpt.client.GptClient
import dev.schlaubi.telegram.deppgpt.database.GptThread
import dev.schlaubi.telegram.deppgpt.utils.blocking
import dev.schlaubi.telegram.deppgpt.utils.reply
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.Clock

context(Bot)
suspend fun TextHandlerEnvironment.handleMessage() {
    if (message.text?.startsWith('/') == true) return
    val input = message.text
    blocking {
        bot.sendChatAction(ChatId.fromId(message.chat.id), ChatAction.TYPING)
    }
    if (input.isNullOrBlank()) {
        reply(GptClient.requestMessage("Wieso kannst du nur Text verstehen?"))
        return
    }

    val conversation = database.threads.find(Filters.eq("_id", message.chat.id)).firstOrNull()

    val newConversation = if (conversation == null) {
        val blankConversation = GptThread(message.chat.id, emptyList())
        blankConversation.requestAnswer(input).also { finalConversation ->
            database.threads.insertOne(finalConversation)
        }
    } else {
        conversation.requestAnswer(input).also { finalConversation ->
            database.threads.updateOne(
                Filters.eq("_id", message.chat.id),
                Updates.set("messages", finalConversation.messages)
            )
        }
    }

    reply(newConversation.messages.last().content)
}
