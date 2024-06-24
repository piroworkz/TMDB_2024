package com.davidluna.architectcoders2024.core_domain.core_entities

import arrow.core.Either
import arrow.core.left
import arrow.core.right

suspend fun <T> tryCatch(block: suspend () -> T): Either<AppError, T> =
    try {
        block().right()
    } catch (e: Throwable) {
        e.toAppError().left()
    }