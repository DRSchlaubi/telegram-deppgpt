package dev.schlaubi.telegram.deppgpt

import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviour
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.setWebhookInfoAndStartListenWebhooks
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.requests.webhook.SetWebhook
import dev.schlaubi.telegram.deppgpt.commands.registerCommands
import dev.schlaubi.telegram.deppgpt.config.Config
import dev.schlaubi.telegram.deppgpt.core.handleMessages
import dev.schlaubi.telegram.deppgpt.database.Database
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.server.netty.*

private val LOG = KotlinLogging.logger { }

class Bot {
    private val telegram = telegramBot(Config.TELEGRAM_API_KEY)
    val database = Database()

    suspend fun start() {
        val botBehavior = telegram.buildBehaviour {
            registerCommands()
            handleMessages()
        }
        if (Config.USE_WEBHOOK) {
            botBehavior.setWebhookInfoAndStartListenWebhooks(
                Config.PORT,
                Netty,
                setWebhookRequest = SetWebhook(Config.HOSTNAME),
                exceptionsHandler = { LOG.error(it) { "An error occurred while handling a request" } },
                block = botBehavior.asUpdateReceiver
            )
        } else {
            botBehavior.startGettingOfUpdatesByLongPolling(updatesReceiver = botBehavior.asUpdateReceiver).join()
        }
    }
}

suspend fun main() = Bot().start()
