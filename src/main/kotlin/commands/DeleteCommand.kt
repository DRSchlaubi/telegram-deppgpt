package dev.schlaubi.telegram.deppgpt.commands

import com.mongodb.client.model.Filters
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.schlaubi.telegram.deppgpt.Bot

context(bot: Bot)
suspend fun BehaviourContext.deleteCommand() = onCommand("delete") {
    bot.database.threads.findOneAndDelete(Filters.eq("_id", it.chat.id.chatId.long))

    reply(it, "Deine Daten wurden erfolgreich gelöscht, bitte beachte, dass erneut welche gespeichert werden, wenn du eine weitere Nachricht schreibst")
}
