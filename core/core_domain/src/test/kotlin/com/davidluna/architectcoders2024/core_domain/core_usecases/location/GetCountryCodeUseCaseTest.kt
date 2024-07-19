package com.davidluna.architectcoders2024.core_domain.core_usecases.location

import com.google.common.truth.Truth.assertThat
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
    fun `GIVEN (invoke is called) WHEN (getCountryCode succeeds) THEN (should return country code as String)`() =
        runTest {
            val expected = "MX"
            whenever(repository.getCountryCode()).thenReturn(expected)

            val actual = repository.getCountryCode()

            assertThat(actual).isEqualTo(expected)
        }
}