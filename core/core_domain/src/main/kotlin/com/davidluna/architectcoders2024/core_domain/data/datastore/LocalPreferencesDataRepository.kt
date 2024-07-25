package com.davidluna.architectcoders2024.core_domain.data.datastore

import com.davidluna.architectcoders2024.core_domain.entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.entities.Session
import com.davidluna.architectcoders2024.core_domain.entities.UserAccount
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalPreferencesDataRepository @Inject constructor(
    private val local: PreferencesDataSource
) : PreferencesRepository {

    override val session: Flow<Session>
        get() = local.session

    override val userAccount: Flow<UserAccount>
        get() = local.userAccount

    override val contentKind: Flow<ContentKind>
        get() = local.contentKind

    override suspend fun closeSession(): Boolean {
       return local.closeSession()
    }

    override suspend fun saveContentKind(contentKind: ContentKind): Boolean {
       return local.saveContentKind(contentKind)
    }
}
