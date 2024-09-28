package com.davidluna.tmdb.core_domain.entities.errors

fun String.buildAppError(): AppError = AppError.Message(
    AppErrorCode.DATASTORE,
    this,
    null
)

