package com.davidluna.architectcoders2024.media_domain.media_domain_usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class FormatDateUseCaseTest {

    @Mock
    lateinit var useCase: FormatDateUseCase

    @Test
    fun `given invoke() receives valid string date when formatDateUseCase is called then should return formatted String date`() =
        runTest {
            val expected = "01 January, 2022"
            whenever(useCase("2022-01-01")).thenReturn(expected)

            val actual = useCase("2022-01-01")

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given invoke() receives invalid date string when formatDateUseCase is called then should return null String`() =
        runTest {
            val expected = null
            whenever(useCase("invalid date string")).thenReturn(expected)

            val actual = useCase("invalid date string")

            Truth.assertThat(actual).isNull()
        }

}