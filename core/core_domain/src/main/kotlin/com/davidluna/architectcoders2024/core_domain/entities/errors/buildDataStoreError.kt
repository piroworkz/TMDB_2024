package com.davidluna.architectcoders2024.core_domain.entities.errors

fun String.buildAppError(): AppError = AppError.Message(
    AppErrorCode.DATASTORE,
    this,
    null
)

