package dev.schlaubi.telegram.deppgpt.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors

private val errorLogger = LoggerFactory.getLogger("ErrorLogger")

private val loomDispatcher = Executors.newVirtualThreadPerTaskExecutor().asCoroutineDispatcher()

private val errorHandler = CoroutineExceptionHandler { _, throwable ->
    errorLogger.error("An error occurred in a coroutine environment", throwable)
}

suspend fun blocking(block: suspend CoroutineScope.() -> Unit) = withContext(loomDispatcher, block)
suspend fun withErrorHandler(block: suspend CoroutineScope.() -> Unit) = withContext(errorHandler, block)
