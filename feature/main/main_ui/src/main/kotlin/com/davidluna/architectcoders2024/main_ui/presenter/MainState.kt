package com.davidluna.architectcoders2024.main_ui.presenter

import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.session.UserAccount

data class MainState(
    val loading: Boolean = false,
    val appError: AppError? = null,
    val user: UserAccount? = null,
    val contentKind: ContentKind = ContentKind.MOVIE
)