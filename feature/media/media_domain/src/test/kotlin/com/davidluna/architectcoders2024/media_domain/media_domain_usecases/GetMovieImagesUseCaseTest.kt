package com.davidluna.architectcoders2024.media_domain.media_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeImages
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMovieImagesUseCaseTest {

    @Mock
    lateinit var repository: MovieDetailsRepository

    @Test
    fun `given invoke() is successful when getMovieImages is called then should return a List of Images on the right sie of Either`() =
        runTest {
            val expected = Either.Right(fakeImages)
            whenever(repository.getMovieImages(any())).thenReturn(expected)

            val actual = repository.getMovieImages("movieId")

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given invoke() fails when getMovieImages is called then should return an AppError on the left side of Either`() =
        runTest {
            val expected = Either.Left(fakeAppError)
            whenever(repository.getMovieImages(any())).thenReturn(expected)

            val actual = repository.getMovieImages("movieId")

            Truth.assertThat(actual).isEqualTo(expected)
        }
}