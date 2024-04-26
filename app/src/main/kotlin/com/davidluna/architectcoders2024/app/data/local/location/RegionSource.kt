package com.davidluna.architectcoders2024.app.data.local.location

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Build
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class RegionSource(
    private val client: FusedLocationProviderClient,
    private val coder: Geocoder
) {

    suspend fun getCountryCode(): String = getFindRegion()

    @SuppressLint("MissingPermission")
    private suspend fun getFindRegion(): String {
        return suspendCancellableCoroutine { continuation ->
            client.lastLocation
                .addOnCompleteListener { task: Task<Location> ->
                    val location: Location? = task.result
                    if (location == null) {
                        continuation.resume(DEFAULT_COUNTRY_CODE)
                    } else {
                        continuation.resume(getCode(location))
                    }
                }
        }
    }

    private fun getCode(location: Location): String = try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            var countryCode: String? = null
            coder.getFromLocation(location.latitude, location.longitude, 1) {
                countryCode = it.firstOrNull()?.countryCode
            }
            countryCode
        } else {
            coder.getFromLocation(location.latitude, location.longitude, 1)
                ?.firstOrNull()?.countryCode
        } ?: DEFAULT_COUNTRY_CODE

    } catch (e: Exception) {
        DEFAULT_COUNTRY_CODE
    }


    companion object {
        const val DEFAULT_COUNTRY_CODE = "US"
    }
}

