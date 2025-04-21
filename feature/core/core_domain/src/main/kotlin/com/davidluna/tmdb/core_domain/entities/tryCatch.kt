package com.davidluna.tmdb.core_domain.entities

import arrow.core.Either
import arrow.core.left
import arrow.core.right

suspend fun <T> tryCatch(block: suspend () -> T): Either<AppError, T> =
    try {
        block().right()
    } catch (appError: AppError) {
        appError.left()
    } catch (e: Throwable) {
        e.toAppError().left()
    }