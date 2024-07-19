package com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeUserAccount
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetUserAccountUseCaseTest {

    @Mock
    lateinit var repository: SessionRepository

    @Test
    fun `GIVEN (invoke is called) WHEN (getUserAccount succeeds) THEN (should return UserAccount on the right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeUserAccount)
            whenever(repository.getUserAccount()).thenReturn(expected)

            val actual = repository.getUserAccount()

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (getUserAccount fails) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(repository.getUserAccount()).thenReturn(expected)

            val actual = repository.getUserAccount()

            Truth.assertThat(actual).isEqualTo(expected)
        }

}