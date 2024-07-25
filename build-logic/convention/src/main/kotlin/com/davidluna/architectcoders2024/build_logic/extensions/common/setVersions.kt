package com.davidluna.architectcoders2024.build_logic.extensions.common

import com.android.build.api.dsl.CompileOptions
import com.davidluna.architectcoders2024.build_logic.constants.Constants

internal fun CompileOptions.setVersions() {
    sourceCompatibility = Constants.JAVA_VERSION
    targetCompatibility = Constants.JAVA_VERSION
}
