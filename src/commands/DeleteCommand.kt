package dev.schlaubi.telegram.deppgpt.commands

import com.mongodb.client.model.Filters
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.schlaubi.telegram.deppgpt.Bot

context(Bot)
suspend fun BehaviourContext.deleteCommand() = onCommand("delete") {
    database.threads.findOneAndDelete(Filters.eq("_id", it.chat.id))

    reply(it, "Deine Daten wurden erfolgreich gelöscht, bitte beachte, dass erneut welche gespeichert werden, wenn du eine weitere Nachricht schreibst")
}
