package com.davidluna.tmdb.auth_domain.data

import arrow.core.Either
import arrow.core.right
import com.davidluna.tmdb.core_domain.entities.errors.DataStoreErrorMessage.SAVE_GUEST_SESSION_ID
import com.davidluna.tmdb.core_domain.entities.errors.DataStoreErrorMessage.SAVE_SESSION_ID
import com.davidluna.tmdb.core_domain.entities.errors.DataStoreErrorMessage.SAVE_USER_ACCOUNT
import com.davidluna.tmdb.core_domain.entities.errors.buildAppError
import com.davidluna.tmdb.test_shared.fakes.fakeGuestSession
import com.davidluna.tmdb.test_shared.fakes.fakeLoginRequest
import com.davidluna.tmdb.test_shared.fakes.fakeSession
import com.davidluna.tmdb.test_shared.fakes.fakeSessionId
import com.davidluna.tmdb.test_shared.fakes.fakeTokenResponse
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.davidluna.tmdb.test_shared.fakes.fakeUserAccount
import com.davidluna.tmdb.test_shared.fakes.guestSessionFake
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
    lateinit var remote: SessionDataSource

    @Mock
    lateinit var local: com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource

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
            val expected = Either.Right(fakeSession)
            whenever(remote.createSessionId(any())).thenReturn(fakeSessionId.right())
            whenever(local.saveSession(any())).thenReturn(true)

            val actual = repository.createSessionId(fakeLoginRequest)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createSessionId(fakeLoginRequest)
            verify(local).saveSession(fakeSession)
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
            whenever(local.saveSession(any())).thenReturn(false)

            val actual = repository.createSessionId(fakeLoginRequest)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createSessionId(fakeLoginRequest)
            verify(local).saveSession(fakeSession)
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
    fun `GIVEN (createGuestSessionId is called) WHEN (remote and local datasource succeed) THEN (should return Session on right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeGuestSession)
            whenever(remote.createGuestSession()).thenReturn(guestSessionFake.right())
            whenever(local.saveSession(any())).thenReturn(true)

            val actual = repository.createGuestSession()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createGuestSession()
            verify(local).saveSession(fakeGuestSession)
        }

    @Test
    fun `GIVEN (createGuestSessionId is called) WHEN (remote createGuestSessionId fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.createGuestSession()).thenReturn(expected)

            val actual = repository.createGuestSession()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createGuestSession()
        }

    @Test
    fun `GIVEN (createGuestSessionId is called) WHEN (local saveIsGuest fails) THEN (should return AppError on leftS side of Either)`() =
        runTest {
            val expected = Either.Left(SAVE_GUEST_SESSION_ID.buildAppError())
            whenever(remote.createGuestSession()).thenReturn(Either.Right(guestSessionFake))
            whenever(local.saveSession(any())).thenReturn(false)

            val actual = repository.createGuestSession()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createGuestSession()
            verify(local).saveSession(fakeGuestSession)
        }

    @Test
    fun `GIVEN (createGuestSessionId is called) WHEN (local saveSessionId fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(SAVE_GUEST_SESSION_ID.buildAppError())
            whenever(remote.createGuestSession()).thenReturn(Either.Right(guestSessionFake))
            whenever(local.saveSession(any())).thenReturn(false)

            val actual = repository.createGuestSession()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).createGuestSession()
            verify(local).saveSession(fakeGuestSession)
        }

}