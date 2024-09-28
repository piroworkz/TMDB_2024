package com.davidluna.tmdb.core_domain.entities

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.toAppError

suspend fun <T> tryCatch(block: suspend () -> T): Either<AppError, T> =
    try {
        block().right()
    } catch (e: Throwable) {
        println("<-- Error in tryCatch: ${e.toAppError()}")
        e.toAppError().left()
    }