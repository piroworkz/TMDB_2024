package com.davidluna.tmdb.media_domain.usecases

import com.davidluna.tmdb.media_domain.usecases.FormatDateUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FormatDateUseCaseTest {

    private val useCase by lazy { FormatDateUseCase() }

    @Test
    fun `GIVEN (invoke() receives valid string date) WHEN (formatDateUseCase is called) THEN (should return formatted String date)`() =
        runTest {
            val expected = "01 January, 2022"

            val actual = useCase("2022-01-01")

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `GIVEN (invoke() receives invalid date string) WHEN (formatDateUseCase is called) THEN (should return null String)`() =
        runTest {
            val expected = null

            val actual = useCase("invalid date string")

            Truth.assertThat(actual).isEqualTo(expected)
        }

}