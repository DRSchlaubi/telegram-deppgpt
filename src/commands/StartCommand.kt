package dev.schlaubi.telegram.deppgpt.commands

import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.command
import dev.schlaubi.telegram.deppgpt.Bot
import dev.schlaubi.telegram.deppgpt.utils.reply

context(Bot)
fun Dispatcher.startCommand() = command("start") {
    reply("Willkommen bei DeppGPT, wie kann ich dir helfen?")
}
