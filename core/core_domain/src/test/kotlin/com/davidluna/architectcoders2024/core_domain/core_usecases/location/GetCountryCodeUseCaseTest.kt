package com.davidluna.architectcoders2024.core_domain.core_usecases.location

import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetCountryCodeUseCaseTest {

    @Mock
    lateinit var repository: RegionRepository

    @Test
    fun `given invoke() is successful when getCountryCode is called then should return country code as String`() =
        runTest {
            val expected = "MX"
            whenever(repository.getCountryCode()).thenReturn(expected)

            val actual = repository.getCountryCode()

            Truth.assertThat(actual).isEqualTo(expected)
        }
}