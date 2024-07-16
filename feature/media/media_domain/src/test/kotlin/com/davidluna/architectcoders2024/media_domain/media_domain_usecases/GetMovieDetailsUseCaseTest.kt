package com.davidluna.architectcoders2024.media_domain.media_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeMediaDetail
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailsUseCaseTest {

    @Mock
    lateinit var repository: MovieDetailsRepository

    @Test
    fun `given invoke() is successful when getMovieDetail is called then should return a List of Cast on the right sie of Either`() =
        runTest {
            val expected = Either.Right(fakeMediaDetail)
            whenever(repository.getMovieDetail(any())).thenReturn(expected)

            val actual = repository.getMovieDetail("movieId")

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given invoke() fails when getMovieDetail is called then should return an AppError on the left side of Either`() =
        runTest {
            val expected = Either.Left(fakeAppError)
            whenever(repository.getMovieDetail(any())).thenReturn(expected)

            val actual = repository.getMovieDetail("movieId")

            Truth.assertThat(actual).isEqualTo(expected)
        }
    
}