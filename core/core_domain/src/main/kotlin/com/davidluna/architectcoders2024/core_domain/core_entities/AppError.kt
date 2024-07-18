package com.davidluna.architectcoders2024.core_domain.core_entities

enum class AppErrorCode {
    UNKNOWN,
    SERVER,
    TIMEOUT,
    NOT_FOUND,
    BAD_REQUEST,
}

sealed class AppError() : Throwable() {

    data class Message(
        val code: AppErrorCode,
        val description: String,
        val type: Throwable? = null
    ) : AppError()

}

fun Throwable.toAppError(): AppError =
    AppError.Message(
        code = AppErrorCode.NOT_FOUND,
        description = this.cause?.message ?: this.message ?: "Unknown error",
        type = cause
    )