package com.davidluna.architectcoders2024.videos_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeMovieVideos
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class VideosDataRepositoryTest {

    @Mock
    lateinit var remote: VideosDataSource

    @Test
    fun `given getVideos() is successful when remote getVideos() is called then should return List of YoutubeVideo on the right side of Either`() =
        runTest {
            val expected = Either.Right(fakeMovieVideos)
            whenever(remote.getVideos(any())).thenReturn(expected)

            val actual = remote.getVideos("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given getVideos() fails when remote getVideos() is called then should return AppError on the left side of Either`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getVideos(any())).thenReturn(expected)

            val actual = remote.getVideos("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
        }

}