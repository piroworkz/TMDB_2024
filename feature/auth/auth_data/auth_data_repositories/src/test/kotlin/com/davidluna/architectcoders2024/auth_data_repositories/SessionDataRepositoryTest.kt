package com.davidluna.architectcoders2024.auth_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.core_data_repositories.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.test_shared.domain.fakeAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeGuestSession
import com.davidluna.architectcoders2024.test_shared.domain.fakeLoginRequest
import com.davidluna.architectcoders2024.test_shared.domain.fakeSessionId
import com.davidluna.architectcoders2024.test_shared.domain.fakeUserAccount
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SessionDataRepositoryTest {

    @Mock
    lateinit var remote: SessionDatasource

    @Mock
    lateinit var local: PreferencesDataSource

    @Test
    fun `given remote createSessionId() is successful when preferences remote createSessionId() is called then should return SessionId on right side of Either`() =
        runTest {
            val expected = Either.Right(fakeSessionId)
            whenever(remote.createSessionId(any())).thenReturn(expected)

            val actual = remote.createSessionId(fakeLoginRequest)
            local.saveSessionId(fakeSessionId.sessionId)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(local).saveSessionId(fakeSessionId.sessionId)
        }

    @Test
    fun `given remote createSessionId() fails when preferences remote createSessionId() is called then should return AppError on left side of Either`() =
        runTest {
            val expected = Either.Left(fakeAppError)
            whenever(remote.createSessionId(any())).thenReturn(expected)

            val actual = remote.createSessionId(fakeLoginRequest)
            local.saveSessionId(fakeSessionId.sessionId)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(local).saveSessionId(fakeSessionId.sessionId)
        }

    @Test
    fun `given remote getUserAccount() is successful when preferences remote getUserAccount() is called then should return UserAccount on right side of Either`() =
        runTest {
            val expected = Either.Right(fakeUserAccount)
            whenever(remote.getUserAccount()).thenReturn(expected)

            val actual = remote.getUserAccount()
            local.saveUser(fakeUserAccount)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(local).saveUser(fakeUserAccount)
        }

    @Test
    fun `given remote getUserAccount() fails when preferences remote getUserAccount() is called then should return AppError on left side of Either`() =
        runTest {
            val expected = Either.Left(fakeAppError)
            whenever(remote.getUserAccount()).thenReturn(expected)

            val actual = remote.getUserAccount()
            local.saveSessionId(fakeSessionId.sessionId)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(local).saveSessionId(fakeSessionId.sessionId)
        }

    @Test
    fun `given remote createGuestSessionId() is successful when preferences remote createGuestSessionId() is called then should return GuestSession on right side of Either`() =
        runTest {
            val expected = Either.Right(fakeGuestSession)
            whenever(remote.createGuestSessionId()).thenReturn(expected)

            val actual = remote.createGuestSessionId()
            local.saveIsGuest(true)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(local).saveIsGuest(true)
        }

    @Test
    fun `given remote createGuestSessionId() fails when preferences remote createGuestSessionId() is called then should return AppError on left side of Either`() =
        runTest {
            val expected = Either.Left(fakeAppError)
            whenever(remote.createGuestSessionId()).thenReturn(expected)

            val actual = remote.createGuestSessionId()
            local.saveSessionId(fakeSessionId.sessionId)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(local).saveSessionId(fakeSessionId.sessionId)
        }


}