package com.soon.backoffice.application.util.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers

object ApplicationDispatchers {
    private val IOName=CoroutineName("io")
    val IO=Dispatchers.IO+ IOName + GlobalCoroutineExceptionHandler
}