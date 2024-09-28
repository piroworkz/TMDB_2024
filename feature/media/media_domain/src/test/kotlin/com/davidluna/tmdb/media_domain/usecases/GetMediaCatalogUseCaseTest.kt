package com.davidluna.tmdb.media_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.test_shared.fakes.fakeMediaResults
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.tmdb.media_domain.usecases.MediaRepository
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
class GetMediaCatalogUseCaseTest {

    @Mock
    lateinit var repository: MediaRepository

    private val useCase by lazy { GetMediaCatalogUseCase(repository) }

    @Test
    fun `GIVEN (invoke() is successful) WHEN (getContent is called) THEN (should return Results os type Media on right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeMediaResults)
            whenever(repository.getMediaCatalog(any(), any())).thenReturn(expected)

            val actual = useCase("popular", 1)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getMediaCatalog("popular", 1)
        }

    @Test
    fun `GIVEN (invoke() fails) WHEN (getContent is called) THEN (should return AppError on left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(repository.getMediaCatalog(any(), any())).thenReturn(expected)

            val actual = useCase("popular", 1)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).getMediaCatalog("popular", 1)
        }
}