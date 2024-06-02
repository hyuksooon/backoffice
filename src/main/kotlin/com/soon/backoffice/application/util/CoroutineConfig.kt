package com.soon.backoffice.application.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.reactor.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.springframework.transaction.support.TransactionSynchronizationManager
import org.springframework.transaction.support.TransactionTemplate
import reactor.core.scheduler.Schedulers
import kotlin.coroutines.CoroutineContext

val boundedElasticDispatcher= Schedulers.boundedElastic().asCoroutineDispatcher()

val boundedElasticScope:CoroutineScope
    get()= CoroutineScope(boundedElasticDispatcher)

suspend fun <T> TransactionTemplate.executeWithContext(
        context: CoroutineContext= boundedElasticDispatcher,
        readOnly: Boolean=false,
        block:CoroutineScope.() -> T,
): T= withContext(context){
    this@executeWithContext.execute {
        if(readOnly) TransactionSynchronizationManager.setCurrentTransactionReadOnly(true)
        block()
    } as T
}