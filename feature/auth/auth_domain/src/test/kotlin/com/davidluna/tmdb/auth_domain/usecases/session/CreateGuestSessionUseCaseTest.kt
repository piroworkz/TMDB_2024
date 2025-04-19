package com.davidluna.tmdb.auth_domain.usecases.session

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.usecases.CreateGuestSessionIdUseCase
import com.davidluna.tmdb.auth_domain.usecases.SessionRepository
import com.davidluna.tmdb.test_shared.fakes.fakeGuestSession
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CreateGuestSessionUseCaseTest {

    @Mock
    lateinit var repository: SessionRepository

    private val useCase by lazy { CreateGuestSessionIdUseCase(repository) }

    @Test
    fun `GIVEN (invoke is called) WHEN (createGuestSessionId succeeds) THEN (should return GuestSession on right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeGuestSession)
            whenever(repository.createGuestSession()).thenReturn(expected)

            val actual = useCase()

            assertThat(actual).isEqualTo(expected)
            verify(repository).createGuestSession()
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (createGuestSessionId fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(repository.createGuestSession()).thenReturn(expected)

            val actual = useCase()

            assertThat(actual).isEqualTo(expected)
            verify(repository).createGuestSession()
        }
}