package com.davidluna.tmdb.fakes

import com.davidluna.tmdb.auth_domain.data.SessionDataRepository
import com.davidluna.tmdb.auth_domain.data.SessionDataSource
import com.davidluna.tmdb.auth_domain.usecases.CreateGuestSessionIdUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateRequestTokenUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateSessionUseCase
import com.davidluna.tmdb.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.tmdb.auth_domain.usecases.GuestSessionNotExpiredUseCase
import com.davidluna.tmdb.auth_domain.usecases.LoginViewModelUseCases
import com.davidluna.tmdb.auth_domain.usecases.SessionRepository
import com.davidluna.tmdb.auth_framework.data.remote.RemoteSessionDataSource
import com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.usecases.datastore.PreferencesRepository
import com.davidluna.tmdb.core_domain.usecases.datastore.SessionUseCase
import com.davidluna.tmdb.test_shared.framework.FakeLocalPreferencesDataSource

class FakeAuthDi {

    private val remote: SessionDataSource by lazy {
        RemoteSessionDataSource(FakeSessionServiceImpl())
    }

    private val local: com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource by lazy {
        FakeLocalPreferencesDataSource()
    }

    private val sessionRepository: SessionRepository by lazy {
        SessionDataRepository(
            remote = remote,
            local = local
        )
    }

    private val preferencesRepository: PreferencesRepository by lazy {
        com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository(
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
