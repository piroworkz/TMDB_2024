package com.davidluna.tmdb.media_data.data

import arrow.core.Either
import com.davidluna.tmdb.media_data.framework.remote.datasources.VideosRemoteDataSource
import com.davidluna.tmdb.media_data.repositories.VideoPlayerDataRepository
import com.davidluna.tmdb.test_shared.fakes.fakeMovieVideos
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class VideoPlayerDataRepositoryTest {

    @Mock
    lateinit var remote: VideosRemoteDataSource

    private lateinit var repository: VideoPlayerDataRepository

    @Before
    fun setUp() {
        repository = VideoPlayerDataRepository(remote)
    }

    @Test
    fun `GIVEN (getVideos is called) WHEN (remote getVideos succeeds) THEN (should return List of YoutubeVideo on the right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeMovieVideos)
            whenever(remote.getVideos(any())).thenReturn(expected)

            val actual = repository.getVideos("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getVideos("endpoint")
        }

    @Test
    fun `GIVEN (getVideos is called) WHEN (remote getVideos fails) THEN (should return AppError on the left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getVideos(any())).thenReturn(expected)

            val actual = repository.getVideos("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getVideos("endpoint")
        }

}