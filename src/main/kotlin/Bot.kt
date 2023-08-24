package dev.schlaubi.telegram.deppgpt

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.telegramError
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.webhook
import dev.schlaubi.telegram.deppgpt.commands.deleteCommand
import dev.schlaubi.telegram.deppgpt.commands.privacyCommand
import dev.schlaubi.telegram.deppgpt.commands.startCommand
import dev.schlaubi.telegram.deppgpt.config.Config
import dev.schlaubi.telegram.deppgpt.core.handleMessage
import dev.schlaubi.telegram.deppgpt.database.Database
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val LOG = LoggerFactory.getLogger(Bot::class.java)

class Bot {
    private val telegram = bot {
        token = Config.TELEGRAM_API_KEY

        dispatch {
            text { handleMessage() }

            deleteCommand()
            startCommand()
            privacyCommand()

            telegramError { LOG.error("Telegram request failed: {}", error) }
        }

        if (Config.USE_WEBHOOK) {
            webhook {
                url = Config.HOSTNAME
            }
        }
    }

    val database = Database()

    fun start() {
        if (Config.USE_WEBHOOK) {
            embeddedServer(Netty) {
                routing {
                    post("/${Config.TELEGRAM_API_KEY}") {
                        telegram.processUpdate(call.receiveText())
                        call.respond(HttpStatusCode.OK)
                    }
                }
            }.start()
            telegram.startWebhook()
        } else {
            telegram.startPolling()
        }
    }

}

fun main() {
    Bot().start()
}
