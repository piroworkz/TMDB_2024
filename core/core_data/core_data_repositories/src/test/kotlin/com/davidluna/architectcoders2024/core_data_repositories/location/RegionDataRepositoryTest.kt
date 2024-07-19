package com.davidluna.architectcoders2024.core_data_repositories.location

import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RegionDataRepositoryTest {

    @Mock
    lateinit var dataSource: RegionDataSource

    private lateinit var repository: RegionDataRepository

    @Before
    fun setUp() {
        repository = RegionDataRepository(dataSource)
    }

    @Test
    fun `GIVEN (getCountryCode is called) WHEN (datasource getCountryCode succeeds) THEN (should return country code from device location as String)`() =
        runTest {
            val expected = "MX"
            whenever(dataSource.getCountryCode()).thenReturn(expected)

            val actual = repository.getCountryCode()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(dataSource).getCountryCode()
        }

}