package com.davidluna.architectcoders2024.fakes

import com.davidluna.architectcoders2024.auth_domain.data.SessionDataRepository
import com.davidluna.architectcoders2024.auth_domain.data.SessionDataSource
import com.davidluna.architectcoders2024.auth_domain.usecases.CreateGuestSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.CreateRequestTokenUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.CreateSessionUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.GuestSessionNotExpiredUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.LoginViewModelUseCases
import com.davidluna.architectcoders2024.auth_domain.usecases.SessionRepository
import com.davidluna.architectcoders2024.auth_framework.data.remote.RemoteSessionDataSource
import com.davidluna.architectcoders2024.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.architectcoders2024.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.PreferencesRepository
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.SessionUseCase
import com.davidluna.architectcoders2024.test_shared.framework.FakeLocalPreferencesDataSource

class FakeAuthDi {

    private val remote: SessionDataSource by lazy {
        RemoteSessionDataSource(FakeSessionServiceImpl())
    }

    private val local: PreferencesDataSource by lazy {
        FakeLocalPreferencesDataSource()
    }

    private val sessionRepository: SessionRepository by lazy {
        SessionDataRepository(
            remote = remote,
            local = local
        )
    }

    private val preferencesRepository: PreferencesRepository by lazy {
        LocalPreferencesDataRepository(
            local = local
        )
    }

    private val createRequestToken by lazy {
        CreateRequestTokenUseCase(sessionRepository)
    }
    private val createSessionId by lazy {
        CreateSessionUseCase(sessionRepository)
    }
    private val createGuestSessionId by lazy {
        CreateGuestSessionIdUseCase(sessionRepository)
    }
    private val getUserAccount by lazy {
        GetUserAccountUseCase(sessionRepository)
    }
    private val sessionId by lazy {
        SessionUseCase(preferencesRepository)
    }
    private val guestSessionNotExpired by lazy {
        GuestSessionNotExpiredUseCase()
    }


    val loginViewModelUseCases by lazy {
        LoginViewModelUseCases(
            createRequestToken,
            createSessionId,
            createGuestSessionId,
            getUserAccount,
            sessionId,
            guestSessionNotExpired
        )
    }
}
