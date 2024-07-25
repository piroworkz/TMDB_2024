package com.davidluna.architectcoders2024.videos_domain.usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import com.davidluna.architectcoders2024.test_shared.fakes.fakeMovieVideos
import com.davidluna.architectcoders2024.test_shared.fakes.fakeUnknownAppError
import com.davidluna.architectcoders2024.videos_domain.entities.YoutubeVideo
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
class GetVideosUseCaseTest {

    @Mock
    lateinit var repository: VideosRepository

    private val useCase by lazy { GetVideosUseCase(repository) }

    @Test
    fun `given invoke() is successful when getVideos is called then should return a List of YoutubeVideo on the right sie of Either`() =
        runTest {
            val expected: Either.Right<List<YoutubeVideo>> = Either.Right(fakeMovieVideos)
            whenever(repository.getVideos(any())).thenReturn(expected)

            val actual = useCase("videos")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getVideos("videos")
        }

    @Test
    fun `given invoke() fails when getVideos is called then should return an AppError on the left side of Either`() =
        runTest {
            val expected: Either.Left<AppError> = Either.Left(fakeUnknownAppError)
            whenever(repository.getVideos(any())).thenReturn(expected)

            val actual = useCase("videos")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getVideos("videos")
        }
}