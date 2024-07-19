package com.davidluna.architectcoders2024.auth_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.core_data_repositories.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.DataStoreErrorMessage.SAVE_GUEST_SESSION_ID
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.DataStoreErrorMessage.SAVE_SESSION_ID
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.DataStoreErrorMessage.SAVE_USER_ACCOUNT
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.buildAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeGuestSession
import com.davidluna.architectcoders2024.test_shared.domain.fakeLoginRequest
import com.davidluna.architectcoders2024.test_shared.domain.fakeSessionId
import com.davidluna.architectcoders2024.test_shared.domain.fakeTokenResponse
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeUserAccount
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
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

    private lateinit var repository: SessionDataRepository

    @Before
    fun setUp() {
        repository = SessionDataRepository(remote, local)
    }

    @Test
    fun `GIVEN (createRequestToken is called) WHEN (remote createRequestToken succeeds) THEN (should return TokenResponse on right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeTokenResponse)
            whenever(remote.createRequestToken()).thenReturn(expected)

            val actual = repository.createRequestToken()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createRequestToken()
        }

    @Test
    fun `GIVEN (createRequestToken is called) WHEN (remote createRequestToken fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.createRequestToken()).thenReturn(expected)

            val actual = repository.createRequestToken()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createRequestToken()
        }

    @Test
    fun `GIVEN (createSessionId is called) WHEN (local saveSessionId && remote createSessionId succeed) THEN (should return SessionId on right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeSessionId)
            whenever(remote.createSessionId(any())).thenReturn(expected)
            whenever(local.saveSessionId(any())).thenReturn(true)

            val actual = repository.createSessionId(fakeLoginRequest)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createSessionId(fakeLoginRequest)
            verify(local).saveSessionId(fakeSessionId.sessionId)
        }

    @Test
    fun `GIVEN (createSessionId is called) WHEN (remote createSessionId fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.createSessionId(any())).thenReturn(expected)

            val actual = repository.createSessionId(fakeLoginRequest)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createSessionId(fakeLoginRequest)
        }

    @Test
    fun `GIVEN (createSessionId is called) WHEN (local saveSessionId fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(SAVE_SESSION_ID.buildAppError())
            whenever(remote.createSessionId(any())).thenReturn(Either.Right(fakeSessionId))
            whenever(local.saveSessionId(any())).thenReturn(false)

            val actual = repository.createSessionId(fakeLoginRequest)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createSessionId(fakeLoginRequest)
            verify(local).saveSessionId(fakeSessionId.sessionId)
        }

    @Test
    fun `GIVEN (getUserAccount is called) WHEN (remote getUserAccount succeeds) THEN (should return UserAccount on right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeUserAccount)
            whenever(remote.getUserAccount()).thenReturn(expected)
            whenever(local.saveUser(any())).thenReturn(true)

            val actual = repository.getUserAccount()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getUserAccount()
            verify(local).saveUser(fakeUserAccount)
        }

    @Test
    fun `GIVEN (getUserAccount is called) WHEN (remote getUserAccount fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getUserAccount()).thenReturn(expected)

            val actual = repository.getUserAccount()

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `GIVEN (getUserAccount is called) WHEN (local saveUser fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(SAVE_USER_ACCOUNT.buildAppError())
            whenever(remote.getUserAccount()).thenReturn(Either.Right(fakeUserAccount))
            whenever(local.saveUser(any())).thenReturn(false)

            val actual = repository.getUserAccount()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getUserAccount()
            verify(local).saveUser(fakeUserAccount)
        }

    @Test
    fun `GIVEN (createGuestSessionId is called) WHEN (remote createGuestSessionId succeeds) THEN (should return GuestSession on right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeGuestSession)
            whenever(remote.createGuestSessionId()).thenReturn(expected)
            whenever(local.saveIsGuest(any())).thenReturn(true)
            whenever(local.saveSessionId(any())).thenReturn(true)

            val actual = repository.createGuestSessionId()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createGuestSessionId()
            verify(local).saveIsGuest(true)
            verify(local).saveSessionId(fakeGuestSession.guestSessionId)
        }

    @Test
    fun `GIVEN (createGuestSessionId is called) WHEN (remote createGuestSessionId fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.createGuestSessionId()).thenReturn(expected)

            val actual = repository.createGuestSessionId()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createGuestSessionId()
        }

    @Test
    fun `GIVEN (createGuestSessionId is called) WHEN (local saveIsGuest fails) THEN (should return AppError on leftS side of Either)`() =
        runTest {
            val expected = Either.Left(SAVE_GUEST_SESSION_ID.buildAppError())
            whenever(remote.createGuestSessionId()).thenReturn(Either.Right(fakeGuestSession))
            whenever(local.saveIsGuest(any())).thenReturn(false)

            val actual = repository.createGuestSessionId()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createGuestSessionId()
            verify(local).saveIsGuest(true)
        }

    @Test
    fun `GIVEN (createGuestSessionId is called) WHEN (local saveSessionId fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(SAVE_GUEST_SESSION_ID.buildAppError())
            whenever(remote.createGuestSessionId()).thenReturn(Either.Right(fakeGuestSession))
            whenever(local.saveIsGuest(any())).thenReturn(true)
            whenever(local.saveSessionId(any())).thenReturn(false)

            val actual = repository.createGuestSessionId()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createGuestSessionId()
            verify(local).saveIsGuest(true)
            verify(local).saveSessionId(fakeGuestSession.guestSessionId)
        }

}