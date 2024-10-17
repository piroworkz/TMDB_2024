package com.davidluna.tmdb.core_data.framework.local

interface CountryCodeDatasource {
    suspend fun getCountryCode(): String
}