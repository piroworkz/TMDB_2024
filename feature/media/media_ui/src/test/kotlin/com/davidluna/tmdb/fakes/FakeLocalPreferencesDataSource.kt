package com.davidluna.tmdb.fakes

import com.davidluna.tmdb.core_data.framework.local.proto_datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.test_shared.fakes.fakeEmptySession
import com.davidluna.tmdb.test_shared.fakes.fakeEmptyUserAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeLocalPreferencesDataSource : PreferencesDataSource {

    private val sessionPreferences = MutableStateFlow(fakeEmptySession)
    private val userPreferences = MutableStateFlow(fakeEmptyUserAccount)
    private val contentKindPreferences = MutableStateFlow(ContentKind.MOVIE)

    override val session: Flow<Session>
        get() = sessionPreferences
    override val userAccount: Flow<UserAccount>
        get() = userPreferences
    override val contentKind: Flow<ContentKind>
        get() = contentKindPreferences

    override suspend fun closeSession(): Boolean {
        return true
    }

    override suspend fun saveSession(session: Session): Boolean {
        sessionPreferences.update { session }
        return sessionPreferences.value == session
    }

    override suspend fun saveUser(user: UserAccount): Boolean {
        userPreferences.update { user }
        return userPreferences.value == user
    }

    override suspend fun saveContentKind(contentKind: ContentKind): Boolean {
        contentKindPreferences.update { contentKind }
        return contentKindPreferences.value == contentKind
    }
}