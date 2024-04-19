package com.davidluna.architectcoders2024.app.utils

import android.util.Log
import com.davidluna.architectcoders2024.app.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.domain.AppError

fun RemoteError.toAppError(): AppError = AppError.Network(
    code = statusCode,
    description = statusMessage,
    successful = success
)



fun String.log(name: String = javaClass.simpleName) = Log.d("<-- $name", this)