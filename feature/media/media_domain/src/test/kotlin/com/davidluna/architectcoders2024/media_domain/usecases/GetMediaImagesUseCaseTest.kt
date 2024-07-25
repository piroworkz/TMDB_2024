package com.davidluna.architectcoders2024.media_domain.usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeImages
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
class GetMediaImagesUseCaseTest {

    @Mock
    lateinit var repository: MovieDetailsRepository

    private val useCase by lazy { GetMediaImagesUseCase(repository) }

    @Test
    fun `given invoke() is successful when getMovieImages is called then should return a List of Images on the right sie of Either`() =
        runTest {
            val expected = Either.Right(fakeImages)
            whenever(repository.getMovieImages(any())).thenReturn(expected)

            val actual = useCase("movieId")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getMovieImages("movieId")
        }

    @Test
    fun `given invoke() fails when getMovieImages is called then should return an AppError on the left side of Either`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(repository.getMovieImages(any())).thenReturn(expected)

            val actual = useCase("movieId")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getMovieImages("movieId")
        }
}