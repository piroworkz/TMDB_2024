package com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeLoginRequest
import com.davidluna.architectcoders2024.test_shared.domain.fakeSession
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
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
class CreateSessionUseCaseTest {

    @Mock
    lateinit var repository: SessionRepository

    private val useCase by lazy { CreateSessionUseCase(repository) }

    @Test
    fun `GIVEN (invoke is called) WHEN (createSessionId succeeds) THEN (should return SessionId on the right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeSession)
            whenever(repository.createSessionId(any())).thenReturn(expected)

            val actual = useCase(fakeLoginRequest)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).createSessionId(fakeLoginRequest)
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (createGuestSessionId fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(repository.createSessionId(any())).thenReturn(expected)

            val actual = useCase(fakeLoginRequest)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).createSessionId(fakeLoginRequest)
        }
}