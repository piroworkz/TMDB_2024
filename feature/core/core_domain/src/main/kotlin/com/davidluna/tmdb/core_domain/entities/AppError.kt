package com.davidluna.tmdb.core_domain.entities

enum class AppErrorCode {
    SERVER,
    NOT_FOUND,
    BAD_REQUEST,
    LOCAL_ERROR
}

data class AppError(
    val code: AppErrorCode,
    val description: String,
    val type: Throwable? = null,
) : Throwable()

fun Throwable.toAppError(): AppError = AppError(
    code = AppErrorCode.NOT_FOUND,
    description = this.message ?: "Unknown error",
    type = cause
)