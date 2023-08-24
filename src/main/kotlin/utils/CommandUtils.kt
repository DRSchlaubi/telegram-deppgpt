package dev.schlaubi.telegram.deppgpt.utils

import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.handlers.TextHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode
import dev.schlaubi.telegram.deppgpt.Bot

context(Bot)
suspend fun CommandHandlerEnvironment.reply(message: String, parseMode: ParseMode? = null) = blocking {
    bot.sendMessage(ChatId.fromId(this@reply.message.chat.id), message, parseMode = parseMode)
}

context(Bot)
suspend fun TextHandlerEnvironment.reply(message: String) = blocking {
    bot.sendMessage(ChatId.fromId(this@reply.message.chat.id), message)
}
