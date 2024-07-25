package com.davidluna.architectcoders2024.build_logic.extensions.application


import com.android.build.api.dsl.ApplicationExtension
import com.davidluna.architectcoders2024.build_logic.constants.Constants.COMPILE_SDK
import com.davidluna.architectcoders2024.build_logic.constants.Constants.MIN_SDK
import com.davidluna.architectcoders2024.build_logic.constants.Constants.NAMESPACE
import com.davidluna.architectcoders2024.build_logic.constants.Constants.TARGET_SDK
import com.davidluna.architectcoders2024.build_logic.constants.Constants.TEST_INSTRUMENTATION_RUNNER
import com.davidluna.architectcoders2024.build_logic.constants.Constants.VERSION_CODE
import com.davidluna.architectcoders2024.build_logic.constants.Constants.VERSION_NAME

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