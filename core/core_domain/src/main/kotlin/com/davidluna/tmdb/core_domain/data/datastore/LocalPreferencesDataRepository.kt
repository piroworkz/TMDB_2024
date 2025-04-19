package com.davidluna.tmdb.core_domain.data.datastore

import com.davidluna.tmdb.core_domain.usecases.datastore.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalPreferencesDataRepository @Inject constructor(
    private val local: PreferencesDataSource
) : PreferencesRepository {

    override val session: Flow<com.davidluna.tmdb.core_domain.entities.Session>
        get() = local.session

    override val userAccount: Flow<com.davidluna.tmdb.core_domain.entities.UserAccount>
        get() = local.userAccount

    override val contentKind: Flow<com.davidluna.tmdb.core_domain.entities.ContentKind>
        get() = local.contentKind

    override suspend fun closeSession(): Boolean {
       return local.closeSession()
    }

    override suspend fun saveContentKind(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind): Boolean {
       return local.saveContentKind(contentKind)
    }
}
