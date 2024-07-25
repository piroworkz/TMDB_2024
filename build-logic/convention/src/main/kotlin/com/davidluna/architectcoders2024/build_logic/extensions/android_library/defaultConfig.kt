package com.davidluna.architectcoders2024.build_logic.extensions.android_library


import com.android.build.api.dsl.LibraryExtension
import com.davidluna.architectcoders2024.build_logic.constants.Constants

internal fun LibraryExtension.defaultConfig() {
    compileSdk = Constants.COMPILE_SDK
    defaultConfig {
        minSdk = Constants.MIN_SDK
        testInstrumentationRunner = Constants.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }
}