package com.davidluna.tmdb.media_domain.data

import arrow.core.Either
import com.davidluna.tmdb.test_shared.fakes.fakeMediaResults
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.davidluna.tmdb.media_domain.data.MediaCatalogDataRepository
import com.davidluna.tmdb.media_domain.data.MediaCatalogRemoteDatasource
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
class MediaCatalogDataRepositoryTest {

    @Mock
    lateinit var remote: MediaCatalogRemoteDatasource

    private lateinit var repository: MediaCatalogDataRepository

    @Before
    fun setUp() {
        repository = MediaCatalogDataRepository(remote)
    }

    @Test
    fun `GIVEN (getContent is called) WHEN (remote getContent succeeds) THEN (should return Results Media on the right side of Either)`() =
        runTest {
            val expected = Either.Right(fakeMediaResults)
            whenever(remote.getMediaCatalog(any(), any())).thenReturn(expected)

            val actual = repository.getMediaCatalog("endpoint", 1)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getMediaCatalog("endpoint", 1)
        }

    @Test
    fun `GIVEN (getContent fails) WHEN (remote getContent is called) THEN (should return AppError on the left side of Either)`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getMediaCatalog(any(), any())).thenReturn(expected)

            val actual = remote.getMediaCatalog("endpoint", 1)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(remote).getMediaCatalog("endpoint", 1)
        }
}