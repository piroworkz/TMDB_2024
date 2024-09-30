package com.davidluna.tmdb.core_framework.di

import android.app.Application
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import org.koin.dsl.module

val coreModule = module {
    single { provideFusedLocationProviderClient(get()) }
    single { provideGeoCoder(get()) }
}

private fun provideFusedLocationProviderClient(application: Application) =
    LocationServices.getFusedLocationProviderClient(application)

private fun provideGeoCoder(application: Application) =
    Geocoder(application)


