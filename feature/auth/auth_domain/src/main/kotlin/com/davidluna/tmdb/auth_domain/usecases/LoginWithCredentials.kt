package com.davidluna.tmdb.auth_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.entities.LoginRequest
import com.davidluna.tmdb.core_domain.entities.AppError

fun interface LoginWithCredentials : suspend (LoginRequest) -> Either<AppError, Unit>