package com.davidluna.architectcoders2024.app.data

import com.davidluna.architectcoders2024.domain.AppError
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException

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