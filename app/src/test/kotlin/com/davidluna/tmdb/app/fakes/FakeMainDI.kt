package com.davidluna.tmdb.app.fakes

import com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.usecases.datastore.CloseSessionUseCase
import com.davidluna.tmdb.core_domain.usecases.datastore.PreferencesRepository
import com.davidluna.tmdb.core_domain.usecases.datastore.SaveContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.datastore.UserAccountUseCase
import com.davidluna.tmdb.test_shared.framework.FakeLocalPreferencesDataSource

class FakeMainDI {

    private val local: com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource by lazy {
        FakeLocalPreferencesDataSource()
    }

    private val preferencesRepository: PreferencesRepository by lazy {
        com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository(
            local = local
        )
    }

    val closeSessionUseCase by lazy {
        CloseSessionUseCase(preferencesRepository)
    }

    val userAccountUseCase by lazy {
        UserAccountUseCase(preferencesRepository)
    }

    val saveContentKindUseCase by lazy {
        SaveContentKindUseCase(preferencesRepository)
    }
}