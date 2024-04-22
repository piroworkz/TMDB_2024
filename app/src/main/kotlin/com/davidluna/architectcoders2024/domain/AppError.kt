package com.davidluna.architectcoders2024.domain

import okio.IOException
import org.json.JSONException
import retrofit2.HttpException

sealed class AppError(
    val statusCode: Int = 0,
    val statusMessage: String = "",
    val success: Boolean = false
) : Throwable() {

    data class Serialization(
        val code: Int,
        val description: String,
        val successful: Boolean
    ) : AppError(code, description, successful)


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
    is JSONException -> AppError.Serialization(
        code = 0,
        description = message.orEmpty(),
        successful = false
    )

    is IOException -> AppError.IO(code = 0, description = message.orEmpty(), successful = false)
    is HttpException -> AppError.Network(
        code = 0,
        description = message.orEmpty(),
        successful = false
    )

    else -> AppError.Unknown(code = 0, description = message.orEmpty(), successful = false)
}