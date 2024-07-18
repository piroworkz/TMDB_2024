package com.davidluna.architectcoders2024.media_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeMediaResults
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MediaCatalogDataRepositoryTest {

    @Mock
    lateinit var remote: MediaCatalogRemoteDatasource

    @Test
    fun `given getContent() is successful when remote getContent() is called then should return Results Media on the right side of Either`() =
        runTest {
            val expected = Either.Right(fakeMediaResults)
            whenever(remote.getContent(any(), any())).thenReturn(expected)

            val actual = remote.getContent("endpoint", 1)

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given getContent() fails when remote getContent() is called then should return AppError on the left side of Either`() =
        runTest {
            val expected = Either.Left(fakeUnknownAppError)
            whenever(remote.getContent(any(), any())).thenReturn(expected)

            val actual = remote.getContent("endpoint", 1)

            Truth.assertThat(actual).isEqualTo(expected)
        }
}