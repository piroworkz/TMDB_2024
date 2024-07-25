package com.davidluna.architectcoders2024.auth_domain.usecases

import com.davidluna.architectcoders2024.core_domain.usecases.datastore.SessionUseCase
import javax.inject.Inject

data class LoginViewModelUseCases @Inject constructor(
    val createRequestToken: CreateRequestTokenUseCase,
    val createSessionId: CreateSessionUseCase,
    val createGuestSessionId: CreateGuestSessionIdUseCase,
    val getUserAccount: GetUserAccountUseCase,
    val sessionId: SessionUseCase,
    val extractQueryArguments: ExtractQueryArgumentsUseCase,
    val guestSessionNotExpired: GuestSessionNotExpiredUseCase
)
