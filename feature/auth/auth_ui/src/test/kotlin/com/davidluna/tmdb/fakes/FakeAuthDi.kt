package com.davidluna.tmdb.fakes

import com.davidluna.tmdb.auth_domain.data.SessionDataRepository
import com.davidluna.tmdb.auth_domain.repositories.SessionDataSource
import com.davidluna.tmdb.auth_domain.usecases.CreateGuestSessionIdUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateRequestTokenUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateSessionUseCase
import com.davidluna.tmdb.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.tmdb.auth_domain.usecases.LoginViewModelUseCases
import com.davidluna.tmdb.auth_domain.repositories.SessionRepository
import com.davidluna.tmdb.auth_data.framework.remote.datasources.SessionService
import com.davidluna.tmdb.fakes.FakeLocalPreferencesDataSource

class FakeAuthDi {

    private val remote: SessionDataSource by lazy {
        SessionService(FakeSessionServiceImpl())
    }

    private val local: com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource by lazy {
        com.davidluna.tmdb.fakes.FakeLocalPreferencesDataSource()
    }

    private val sessionRepository: SessionRepository by lazy {
        SessionDataRepository(
            remote = remote,
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


    val loginViewModelUseCases by lazy {
        LoginViewModelUseCases(
            createRequestToken,
            createSessionId,
            createGuestSessionId,
            getUserAccount
        )
    }
}
