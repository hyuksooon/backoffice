package com.soon.backoffice.domain.extension

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.web.server.ServerWebInputException
import java.util.*
import kotlin.reflect.KClass

val objectMapper: ObjectMapper = ObjectMapper().apply {
    registerModule(JavaTimeModule().apply {
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS)
        enable (MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
    })
}

val exceptNullObjectMapper: ObjectMapper = objectMapper.copy().apply {
    setSerializationInclusion (JsonInclude.Include.NON_NULL)
}
private val PrettyPrinter by lazy { objectMapper.writerWithDefaultPrettyPrinter() }

fun Any.toJson(): String = objectMapper.writeValueAsString(this)

fun Any. toPrettyJson(): String = PrettyPrinter.writeValueAsString(this)

inline  fun <reified T> String.toModel(): T = objectMapper.readValue(this)

fun <T : Any> String.toModel0rNull(modelType: KClass<T>): T? = try {
    objectMapper.readValue(this, modelType.java)
}catch (e: Exception) {
    null
}


inline fun <reified T> String.toModel0rThrow(): T {
    return try {
        objectMapper.readValue(this, jacksonTypeRef<T>())
    } catch (e: MismatchedInputException) {
        throw ServerWebInputException("${this::class}", null, e)
    }
}

fun Any.toSingleMap(): Map<String, Any?> = objectMapper.convertValue( this)

fun Any.encodeDtoToBase64(): String {
    return Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(this).toByteArray())
}

inline fun <reified T> String.decodeBase64ToDto(): T {
    return objectMapper.readValue(String(Base64.getDecoder().decode(this)))
}