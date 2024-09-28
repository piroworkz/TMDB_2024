package com.davidluna.tmdb.convention.extensions.common

import com.android.build.api.dsl.CompileOptions
import com.davidluna.tmdb.convention.constants.Constants

internal fun CompileOptions.setVersions() {
    sourceCompatibility = Constants.JAVA_VERSION
    targetCompatibility = Constants.JAVA_VERSION
}
