package com.davidluna.architectcoders2024.build_logic.constants

import org.gradle.api.JavaVersion

object Constants {
    const val NAMESPACE = "com.davidluna.architectcoders2024"
    const val COMPILE_SDK = 34
    const val MIN_SDK = 26
    const val TARGET_SDK = 34
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val API_KEY = "MY_API_KEY"
    const val BASE_URL = "BASE_URL"
    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.11"

    val JAVA_VERSION = JavaVersion.VERSION_17
}