package com.davidluna.architectcoders2024.convention.extensions.application


import com.android.build.api.dsl.ApplicationExtension
import com.davidluna.architectcoders2024.convention.constants.Constants.COMPILE_SDK
import com.davidluna.architectcoders2024.convention.constants.Constants.MIN_SDK
import com.davidluna.architectcoders2024.convention.constants.Constants.NAMESPACE
import com.davidluna.architectcoders2024.convention.constants.Constants.TARGET_SDK
import com.davidluna.architectcoders2024.convention.constants.Constants.TEST_INSTRUMENTATION_RUNNER
import com.davidluna.architectcoders2024.convention.constants.Constants.VERSION_CODE
import com.davidluna.architectcoders2024.convention.constants.Constants.VERSION_NAME

internal fun ApplicationExtension.setDefaultConfig() {
    namespace = NAMESPACE
    compileSdk = COMPILE_SDK

    defaultConfig {
        applicationId = NAMESPACE
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
        versionCode = VERSION_CODE
        versionName = VERSION_NAME
        testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }
}