package com.davidluna.architectcoders2024.data.repositories.preferences

import com.davidluna.architectcoders2024.data.sources.preferences.PreferencesDataSource
import com.davidluna.architectcoders2024.domain.ContentKind
import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.architectcoders2024.usecases.repositories.LocalPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalPreferencesDataRepository @Inject constructor(
    private val local: PreferencesDataSource
) : LocalPreferencesRepository {

    override val sessionId: Flow<String>
        get() = local.sessionId

    override val userAccount: Flow<UserAccount>
        get() = local.userAccount

    override val isGuest: Flow<Boolean>
        get() = local.isGuest

    override val contentKind: Flow<ContentKind>
        get() = local.contentKind


    override suspend fun closeSession() = local.closeSession()

    override suspend fun saveContentKind(contentKind: ContentKind) =
        local.saveContentKind(contentKind)
}



