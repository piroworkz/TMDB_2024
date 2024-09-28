package com.davidluna.tmdb.auth_domain.usecases.session

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.usecases.CreateRequestTokenUseCase
import com.davidluna.tmdb.auth_domain.usecases.SessionRepository
import com.davidluna.tmdb.test_shared.fakes.fakeTokenResponse
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CreateRequestTokenUseCaseTest {

    @Mock
    lateinit var repository: SessionRepository

    private val useCase by lazy { CreateRequestTokenUseCase(repository) }

    @Test
    fun `GIVEN (invoke is called) WHEN (createRequestToken succeeds) THEN (should return TokenResponse on right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeTokenResponse)
            whenever(repository.createRequestToken()).thenReturn(expected)

            val actual = useCase()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).createRequestToken()
        }

    @Test
    fun `GIVEN (invoke ia called) WHEN (createRequestToken fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(repository.createRequestToken()).thenReturn(expected)

            val actual = useCase()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).createRequestToken()
        }

}