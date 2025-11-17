package dev.schlaubi.telegram.deppgpt.commands

import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.inmo.tgbotapi.types.message.MarkdownParseMode
import dev.schlaubi.telegram.deppgpt.Bot

context(_:Bot)
suspend fun BehaviourContext.privacyCommand() = onCommand("privacy") {
    reply(
        it,
        """
        Dieser Bot speichert die folgenden Daten sobald du eine Nachricht (ausgeschlossen Befehle) an ihn schickst:
        
        - ID des Chats zwischen dir und dem Bot
        - Anzahl aller Nachrichten von dir und vom Bot
        
        Alle diese Daten können durch ausführen von /delete gelöscht werden
        
        Alle Nachrichten werden mit dem Postillon geteilt, dessen Datenschutzerklärung [hier](https://www.der-postillon.com/p/blog-page_9.html) auffindbar ist
        
        **Dieser Bot steht in keiner offiziellen Partnerschaft mit dem Postillon oder Steckenpferd Enterprises UG**
    """.trimIndent(), parseMode = MarkdownParseMode
    )
}
