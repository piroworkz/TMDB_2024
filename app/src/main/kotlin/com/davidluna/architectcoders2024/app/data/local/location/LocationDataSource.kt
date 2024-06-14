package com.davidluna.architectcoders2024.app.data.local.location

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Build
import com.davidluna.architectcoders2024.data.sources.location.RegionDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationDataSource @Inject constructor(
    private val client: FusedLocationProviderClient,
    private val coder: Geocoder
) : RegionDataSource {

    override suspend fun getCountryCode(): String = findRegion()

    @SuppressLint("MissingPermission")
    private suspend fun findRegion(): String {
        return suspendCancellableCoroutine { continuation ->
            client.lastLocation
                .addOnCompleteListener { task: Task<Location> ->
                    val location: Location? = task.result
                    if (location == null) {
                        client.requestLocationUpdates(
                            buildRequest(),
                            continuation.getLocationCallback(),
                            null
                        )
                        client.removeLocationUpdates(continuation.getLocationCallback())
                    } else {
                        continuation.resume(getCode(location))
                    }
                }
        }
    }


    private fun buildRequest(): LocationRequest {
        return LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()
    }

    private fun CancellableContinuation<String>.getLocationCallback(): LocationCallback =
        object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                if (isCompleted) return
                val location = result.lastLocation
                if (location != null) {
                    val code = getCode(location)
                    resume(code)
                } else {
                    resume(DEFAULT_COUNTRY_CODE)
                }
            }
        }

    private fun getCode(location: Location): String =
        try {
            val code = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                var countryCode: String? = null
                coder.getFromLocation(location.latitude, location.longitude, 1) {
                    countryCode = it.firstOrNull()?.countryCode
                }
                countryCode
            } else {
                coder.getFromLocation(location.latitude, location.longitude, 1)
                    ?.firstOrNull()?.countryCode
            } ?: DEFAULT_COUNTRY_CODE
            code
        } catch (e: Exception) {
            DEFAULT_COUNTRY_CODE
        }


    companion object {
        const val DEFAULT_COUNTRY_CODE = "MX"
    }
}

