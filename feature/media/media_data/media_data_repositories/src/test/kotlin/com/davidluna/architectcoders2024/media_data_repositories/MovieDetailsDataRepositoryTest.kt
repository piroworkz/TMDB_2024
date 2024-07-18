package com.davidluna.architectcoders2024.media_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeCastList
import com.davidluna.architectcoders2024.test_shared.domain.fakeImages
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
class MovieDetailsDataRepositoryTest {

    @Mock
    lateinit var remote: MovieDetailsDataSource

    @Test
    fun `given getMovieDetail() is successful when remote getMovieDetail() is called then should return MediaDetails on the right side of Either`() =
        runTest {
            val expected = Either.Right(fakeMediaDetail)
            whenever(remote.getMovieDetail(any())).thenReturn(expected)

            val actual = remote.getMovieDetail("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given getMovieDetail() fails when remote getMovieDetail() is called then should return AppError on the left side of Either`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getMovieDetail(any())).thenReturn(expected)

            val actual = remote.getMovieDetail("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
        }


    @Test
    fun `given getMovieCast() is successful when remote getMovieCast() is called then should return List of Cast on the right side of Either`() =
        runTest {
            val expected = Either.Right(fakeCastList)
            whenever(remote.getMovieCast(any())).thenReturn(expected)

            val actual = remote.getMovieCast("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given getMovieCast() fails when remote getMovieCast() is called then should return AppError on the left side of Either`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getMovieCast(any())).thenReturn(expected)

            val actual = remote.getMovieCast("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given getMovieImages() is successful when remote getMovieImages() is called then should return List of Image on the right side of Either`() =
        runTest {
            val expected = Either.Right(fakeImages)
            whenever(remote.getMovieImages(any())).thenReturn(expected)

            val actual = remote.getMovieImages("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given getMovieImages() fails when remote getMovieImages() is called then should return AppError on the left side of Either`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getMovieImages(any())).thenReturn(expected)

            val actual = remote.getMovieImages("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
        }


}