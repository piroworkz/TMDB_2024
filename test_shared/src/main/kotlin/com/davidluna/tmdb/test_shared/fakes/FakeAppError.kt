package com.davidluna.tmdb.test_shared.fakes

import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.AppErrorCode
import com.davidluna.tmdb.core_domain.entities.errors.AppErrorCode.UNKNOWN

val fakeUnknownAppError: AppError = AppError.Message(code= UNKNOWN, description="Unknown error", type=null)
val fakeNotFoundAppError: AppError = AppError.Message(code= AppErrorCode.NOT_FOUND, description="Unknown error", type=null)
