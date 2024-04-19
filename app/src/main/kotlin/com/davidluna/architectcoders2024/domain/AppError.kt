package com.davidluna.architectcoders2024.domain

import kotlinx.serialization.Serializable
import okio.IOException
import retrofit2.HttpException

sealed class AppError(
    val statusCode: Int = 0,
    val statusMessage: String = "",
    val success: Boolean = false
) : Throwable() {


    data class Unknown(
        val code: Int,
        val description: String,
        val successful: Boolean
    ) : AppError(code, description, successful)

    data class Network(
        val code: Int,
        val description: String,
        val successful: Boolean
    ) : AppError(code, description, successful)

    data class IO(
        val code: Int,
        val description: String,
        val successful: Boolean
    ) : AppError(code, description, successful)
}

fun Throwable.toAppError(): AppError = when (this) {
    is IOException -> AppError.IO(code = 0, description = message.orEmpty(), successful = false)
    is HttpException -> AppError.Network(
        code = 0,
        description = message.orEmpty(),
        successful = false
    )

    else -> AppError.Unknown(code = 0, description = message.orEmpty(), successful = false)
}