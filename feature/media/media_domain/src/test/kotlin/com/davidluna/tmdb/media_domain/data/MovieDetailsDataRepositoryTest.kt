package com.davidluna.tmdb.media_domain.data

import arrow.core.Either
import com.davidluna.tmdb.test_shared.fakes.fakeCastList
import com.davidluna.tmdb.test_shared.fakes.fakeImages
import com.davidluna.tmdb.test_shared.fakes.fakeMediaDetail
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.davidluna.tmdb.media_domain.data.MovieDetailsDataRepository
import com.davidluna.tmdb.media_domain.data.MovieDetailsDataSource
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
class MovieDetailsDataRepositoryTest {

    @Mock
    lateinit var remote: MovieDetailsDataSource

    private lateinit var repository: MovieDetailsDataRepository

    @Before
    fun setUp() {
        repository = MovieDetailsDataRepository(remote)
    }

    @Test
    fun `GIVEN (getMovieDetail is called) WHEN (remote getMovieDetail succeeds) THEN (should return MediaDetails on the right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeMediaDetail)
            whenever(remote.getMovieDetail(any())).thenReturn(expected)

            val actual = repository.getMovieDetail("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getMovieDetail("endpoint")
        }

    @Test
    fun `GIVEN (getMovieDetail is called) WHEN (remote getMovieDetail fails) THEN (should return AppError on the left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getMovieDetail(any())).thenReturn(expected)

            val actual = repository.getMovieDetail("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getMovieDetail("endpoint")
        }


    @Test
    fun `GIVEN (getMovieCast is called) WHEN (remote getMovieCast succeeds) THEN (should return List of Cast on the right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeCastList)
            whenever(remote.getMovieCast(any())).thenReturn(expected)

            val actual = repository.getMovieCast("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getMovieCast("endpoint")
        }

    @Test
    fun `GIVEN (getMovieCast is called) WHEN (remote getMovieCast fails) THEN (should return AppError on the left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getMovieCast(any())).thenReturn(expected)

            val actual = repository.getMovieCast("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getMovieCast("endpoint")
        }

    @Test
    fun `GIVEN (getMovieImages is called) WHEN (remote getMovieImages succeeds) THEN (should return List of Image on the right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeImages)
            whenever(remote.getMovieImages(any())).thenReturn(expected)

            val actual = repository.getMovieImages("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getMovieImages("endpoint")
        }

    @Test
    fun `GIVEN (getMovieImages is called) WHEN (remote getMovieImages fails) THEN (should return AppError on the left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getMovieImages(any())).thenReturn(expected)

            val actual = repository.getMovieImages("endpoint")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getMovieImages("endpoint")
        }


}