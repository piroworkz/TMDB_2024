package com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeGuestSession
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CreateGuestSessionIdUseCaseTest {

    @Mock
    lateinit var repository: SessionRepository

    @Test
    fun `GIVEN (invoke is called) WHEN (createGuestSessionId succeeds) THEN (should return GuestSession on right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeGuestSession)
            whenever(repository.createGuestSessionId()).thenReturn(expected)

            val actual = repository.createGuestSessionId()

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (createGuestSessionId fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(repository.createGuestSessionId()).thenReturn(expected)

            val actual = repository.createGuestSessionId()

            Truth.assertThat(actual).isEqualTo(expected)
        }
}