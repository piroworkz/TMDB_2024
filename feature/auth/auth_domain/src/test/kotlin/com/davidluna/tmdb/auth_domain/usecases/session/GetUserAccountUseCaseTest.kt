package com.davidluna.tmdb.auth_domain.usecases.session

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.tmdb.auth_domain.usecases.SessionRepository
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.davidluna.tmdb.test_shared.fakes.fakeUserAccount
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetUserAccountUseCaseTest {

    @Mock
    lateinit var repository: SessionRepository

    private val useCase by lazy { GetUserAccountUseCase(repository) }

    @Test
    fun `GIVEN (invoke is called) WHEN (getUserAccount succeeds) THEN (should return UserAccount on the right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeUserAccount)
            whenever(repository.getUserAccount()).thenReturn(expected)

            val actual = useCase()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getUserAccount()
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (getUserAccount fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(repository.getUserAccount()).thenReturn(expected)

            val actual = useCase()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getUserAccount()
        }

}