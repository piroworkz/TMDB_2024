package com.davidluna.tmdb.media_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.test_shared.fakes.fakeCastList
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.davidluna.tmdb.media_domain.usecases.GetMediaCastUseCase
import com.davidluna.tmdb.media_domain.usecases.MovieDetailsRepository
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
class GetMediaCastUseCaseTest {

    @Mock
    lateinit var repository: MovieDetailsRepository

    private val useCase by lazy { GetMediaCastUseCase(repository) }

    @Test
    fun `GIVEN (invoke() is successful) WHEN (getMovieCast is called) THEN (should return a List of Cast on the right sie of Either)`() =
        runTest {
            val expected = Either.Right(fakeCastList)
            whenever(repository.getMovieCast(any())).thenReturn(expected)

            val actual = useCase("movieId")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getMovieCast("movieId")
        }

    @Test
    fun `GIVEN invoke() fails) WHEN (getMovieCast is called) THEN (should return an AppError on the left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(repository.getMovieCast(any())).thenReturn(expected)

            val actual = useCase("movieId")

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getMovieCast("movieId")
        }
}