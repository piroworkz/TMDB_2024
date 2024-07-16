package com.davidluna.architectcoders2024.media_domain.media_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeMediaResults
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetContentUseCaseTest {

    @Mock
    lateinit var repository: MoviesRepository

    @Test
    fun `given invoke() is successful when getContent is called then should return Results os type Media on right side of Either`() =
        runTest {
            val expected = Either.Right(fakeMediaResults)
            whenever(repository.getContent(any(), any())).thenReturn(expected)
            val actual = repository.getContent("popular", 1)

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given invoke() fails when getContent is called then should return AppError on left side of Either`() =
        runTest {
            val expected = Either.Left(fakeAppError)
            whenever(repository.getContent(any(), any())).thenReturn(expected)

            val actual = repository.getContent("popular", 1)

            Truth.assertThat(actual).isEqualTo(expected)
        }
}