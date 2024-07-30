package com.davidluna.architectcoders2024.fakes

import com.davidluna.architectcoders2024.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.architectcoders2024.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.CloseSessionUseCase
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.PreferencesRepository
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.SaveContentKindUseCase
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.UserAccountUseCase
import com.davidluna.architectcoders2024.test_shared.framework.FakeLocalPreferencesDataSource

class FakeMainDI {

    private val local: PreferencesDataSource by lazy {
        FakeLocalPreferencesDataSource()
    }

    private val preferencesRepository: PreferencesRepository by lazy {
        LocalPreferencesDataRepository(
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