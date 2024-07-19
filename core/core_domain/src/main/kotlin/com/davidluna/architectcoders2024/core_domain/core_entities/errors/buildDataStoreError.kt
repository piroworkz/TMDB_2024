package com.davidluna.architectcoders2024.core_domain.core_entities.errors

fun String.buildAppError(): AppError = AppError.Message(
    AppErrorCode.DATASTORE,
    this,
    null
)

