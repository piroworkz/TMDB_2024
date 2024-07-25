package com.davidluna.architectcoders2024.media_domain.usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeMediaDetail
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
import com.davidluna.architectcoders2024.videos_domain.usecases.GetVideosUseCase
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
class GetMediaDetailsUseCaseTest {

    @Mock
    lateinit var repository: MovieDetailsRepository

    @Mock
    lateinit var formatDate: FormatDateUseCase

    @Mock
    lateinit var getVideosUseCase: GetVideosUseCase

    private val useCase by lazy { GetMediaDetailsUseCase(repository, formatDate, getVideosUseCase) }

    @Test
    fun `given invoke() is successful when getMovieDetail is called then should return a List of Cast on the right sie of Either`() =
        runTest {
            val expected = Either.Right(fakeMediaDetail)
            whenever(repository.getMovieDetail(any())).thenReturn(expected)
            whenever(getVideosUseCase(any())).thenReturn(Either.Right(emptyList()))
            whenever(formatDate(any())).thenReturn("01 January, 2022")

            val actual = useCase("movieId")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getMovieDetail("movieId")
            verify(getVideosUseCase).invoke("movieId")
            verify(formatDate).invoke("01 January, 2022")
        }

    @Test
    fun `given invoke() fails when getMovieDetail is called then should return an AppError on the left side of Either`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(repository.getMovieDetail(any())).thenReturn(expected)

            val actual = useCase("movieId")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getMovieDetail("movieId")
        }

}