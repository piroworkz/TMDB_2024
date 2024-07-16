package com.davidluna.architectcoders2024.test_shared.domain

import com.davidluna.architectcoders2024.core_domain.core_entities.AppError

val fakeAppError: AppError = AppError.Message(
    code = 0,
    description = "Fake error message",
    type = IllegalStateException("Fake exception")
)


