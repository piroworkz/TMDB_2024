package com.davidluna.architectcoders2024.core_data_repositories.datastore

import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.session.UserAccount
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.LocalPreferencesRepository
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


    override suspend fun closeSession(): Boolean = local.closeSession()

    override suspend fun saveContentKind(contentKind: ContentKind): Boolean =
        local.saveContentKind(contentKind)
}



