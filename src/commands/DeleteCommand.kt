package dev.schlaubi.telegram.deppgpt.commands

import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.command
import com.mongodb.client.model.Filters
import dev.schlaubi.telegram.deppgpt.Bot
import dev.schlaubi.telegram.deppgpt.utils.reply

context(Bot)
fun Dispatcher.deleteCommand() = command("delete") {
    database.threads.findOneAndDelete(Filters.eq("_id", message.chat.id))

    reply("Deine Daten wurden erfolgreich gelöscht, bitte beachte, dass erneut welche gespeichert werden, wenn du eine weitere Nachricht schreibst")
}
