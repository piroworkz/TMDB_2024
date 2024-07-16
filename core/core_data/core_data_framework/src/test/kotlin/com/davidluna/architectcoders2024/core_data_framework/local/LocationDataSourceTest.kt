package com.davidluna.architectcoders2024.core_data_framework.local

import android.location.Geocoder
import com.davidluna.architectcoders2024.core_data_repositories.location.RegionDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import javax.inject.Inject

class LocationDataSource @Inject constructor(
    private val client: FusedLocationProviderClient,
    private val coder: Geocoder
) : RegionDataSource {
    override suspend fun getCountryCode(): String {
        TODO("Not yet implemented")
    }
}