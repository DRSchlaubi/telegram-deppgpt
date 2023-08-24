package dev.schlaubi.telegram.deppgpt.commands

import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ParseMode
import dev.schlaubi.telegram.deppgpt.Bot
import dev.schlaubi.telegram.deppgpt.utils.reply

context(Bot)
fun Dispatcher.privacyCommand() = command("privacy") {
    reply("""
        Dieser Bot speichert die folgenden Daten sobald du eine Nachricht (ausgeschlossen Befehle) an ihn schickst:
        
        - ID des Chats zwischen dir und dem Bot
        - Anzahl aller Nachrichten von dir und vom Bot
        
        Alle diese Daten können durch ausführen von /delete gelöscht werden
        
        Alle Nachrichten werden mit dem Postillon geteilt, dessen Datenschutzerklärung [hier](https://www.der-postillon.com/p/blog-page_9.html) auffindbar ist
        
        **Dieser Bot steht in keiner offiziellen Partnerschaft mit dem Postillon oder Steckenpferd Enterprises UG**
    """.trimIndent(), parseMode = ParseMode.MARKDOWN_V2)
}
