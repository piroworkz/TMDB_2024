package com.davidluna.tmdb.auth_domain.usecases

data class LoginViewModelUseCases (
    val createRequestToken: CreateRequestTokenUseCase,
    val createSessionId: CreateSessionUseCase,
    val createGuestSessionId: CreateGuestSessionIdUseCase,
    val getUserAccount: GetUserAccountUseCase,
    val guestSessionNotExpired: GuestSessionNotExpiredUseCase
)
