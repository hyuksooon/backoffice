package com.soon.backoffice.application.util.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import org.slf4j.LoggerFactory
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

object GlobalCoroutineExceptionHandler: AbstractCoroutineContextElement(CoroutineExceptionHandler),
        CoroutineExceptionHandler{
        private val log = LoggerFactory.getLogger(javaClass)

        override fun handleException(context: CoroutineContext, exception: Throwable) {
                val name = context[CoroutineName]?.name ?: "coroutuine"
                val description=context[CoroutineDescription]?.description
                log.error("$name failed. : $description", exception)
                for (suppressed in exception.suppressed) {
                        log.error("$name has suppressed error",suppressed)
                }
        }
}