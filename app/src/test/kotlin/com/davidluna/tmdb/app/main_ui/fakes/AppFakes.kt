package com.davidluna.tmdb.app.main_ui.fakes

import com.davidluna.tmdb.auth_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.AppError
import com.davidluna.tmdb.core_domain.entities.AppErrorCode

val fakeUserAccount = UserAccount(
    userId = 3890,
    name = "Joni McKnight",
    username = "Esmeralda Savage",
    avatarPath = "utroque"
)

val fakeAppError = AppError(
    code = AppErrorCode.NOT_FOUND, description = "Fake failure", type = null
)