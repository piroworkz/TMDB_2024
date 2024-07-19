package com.davidluna.architectcoders2024.videos_domain.videos_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeMovieVideos
import com.davidluna.architectcoders2024.videos_domain.videos_domain_entities.YoutubeVideo
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetVideosUseCaseTest {

    @Mock
    lateinit var repository: VideosRepository

    @Test
    fun `given invoke() is successful when getVideos is called then should return a List of YoutubeVideo on the right sie of Either`() =
        runTest {
            val expected: Either.Right<List<YoutubeVideo>> = Either.Right(fakeMovieVideos)
            whenever(repository.getVideos(any())).thenReturn(expected)

            val actual = repository.getVideos("videos")

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given invoke() fails when getVideos is called then should return an AppError on the left side of Either`() =
        runTest {
            val expected: Either.Left<AppError> = Either.Left(fakeUnknownAppError)
            whenever(repository.getVideos(any())).thenReturn(expected)

            val actual = repository.getVideos("videos")

            Truth.assertThat(actual).isEqualTo(expected)
        }
}