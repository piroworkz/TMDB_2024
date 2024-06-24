package com.davidluna.architectcoders2024.core_domain.core_entities

sealed class AppError() : Throwable() {

    data class Message(
        val code: Int,
        val description: String,
        val type: Throwable? = null
    ) : AppError()

}

fun Throwable.toAppError(): AppError =
    AppError.Message(
        code = hashCode(),
        description = message ?: "",
        type = cause
    )