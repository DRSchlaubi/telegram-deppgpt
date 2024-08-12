package dev.schlaubi.telegram.deppgpt.commands

import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.schlaubi.telegram.deppgpt.Bot

context(Bot)
suspend fun BehaviourContext.startCommand() = onCommand("start") {
    reply(it, "Willkommen bei DeppGPT, wie kann ich dir helfen?")
}
