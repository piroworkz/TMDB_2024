package com.davidluna.tmdb.core_domain.data.datastore

import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.repositories.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class LocalPreferencesDataRepository(
    private val local: PreferencesDataSource,
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
