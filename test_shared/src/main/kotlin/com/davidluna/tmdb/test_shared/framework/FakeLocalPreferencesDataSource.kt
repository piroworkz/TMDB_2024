package com.davidluna.tmdb.test_shared.framework

import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.test_shared.fakes.fakeEmptySession
import com.davidluna.tmdb.test_shared.fakes.fakeEmptyUserAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeLocalPreferencesDataSource :
    com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource {

    private val sessionPreferences = MutableStateFlow(fakeEmptySession)
    private val userPreferences = MutableStateFlow(fakeEmptyUserAccount)
    private val contentKindPreferences = MutableStateFlow(com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE)

    override val session: Flow<com.davidluna.tmdb.core_domain.entities.Session>
        get() = sessionPreferences
    override val userAccount: Flow<com.davidluna.tmdb.core_domain.entities.UserAccount>
        get() = userPreferences
    override val contentKind: Flow<com.davidluna.tmdb.core_domain.entities.ContentKind>
        get() = contentKindPreferences

    override suspend fun closeSession(): Boolean {
        return true
    }

    override suspend fun saveSession(session: com.davidluna.tmdb.core_domain.entities.Session): Boolean {
        sessionPreferences.update { session }
        return sessionPreferences.value == session
    }

    override suspend fun saveUser(user: com.davidluna.tmdb.core_domain.entities.UserAccount): Boolean {
        userPreferences.update { user }
        return userPreferences.value == user
    }

    override suspend fun saveContentKind(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind): Boolean {
        contentKindPreferences.update { contentKind }
        return contentKindPreferences.value == contentKind
    }
}