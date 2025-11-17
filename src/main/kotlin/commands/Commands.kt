package dev.schlaubi.telegram.deppgpt.commands

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.schlaubi.telegram.deppgpt.Bot

context(_: Bot)
suspend fun BehaviourContext.registerCommands() {
    deleteCommand()
    privacyCommand()
    startCommand()
}
