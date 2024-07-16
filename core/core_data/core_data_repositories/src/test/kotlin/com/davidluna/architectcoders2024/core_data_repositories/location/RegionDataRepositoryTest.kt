package com.davidluna.architectcoders2024.core_data_repositories.location

import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RegionDataRepositoryTest {

    @Mock
    lateinit var dataSource: RegionDataSource

    @Test
    fun `given getCountryCode() is successful when preferences getCountryCode() is called then should return country code as String`() =
        runTest {
            val expected = "MX"
            whenever(dataSource.getCountryCode()).thenReturn(expected)

            val actual = dataSource.getCountryCode()

            Truth.assertThat(actual).isEqualTo(expected)
        }

}