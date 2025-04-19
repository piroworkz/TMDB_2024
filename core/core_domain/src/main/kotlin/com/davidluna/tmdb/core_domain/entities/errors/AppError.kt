package com.davidluna.tmdb.core_domain.entities.errors

enum class AppErrorCode {
    DATASTORE,
    UNKNOWN,
    SERVER,
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