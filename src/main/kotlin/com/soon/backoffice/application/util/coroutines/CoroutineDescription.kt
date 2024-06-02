package com.soon.backoffice.application.util.coroutines

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class CoroutineDescription(val description: String) : AbstractCoroutineContextElement(CoroutineDescription) {

    companion object Key: CoroutineContext.Key<CoroutineDescription>

    override fun toString(): String ="CoroutineDescription($description)"
}