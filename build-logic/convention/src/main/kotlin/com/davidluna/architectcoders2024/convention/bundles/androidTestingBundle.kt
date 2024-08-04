package com.davidluna.architectcoders2024.convention.bundles

import com.davidluna.architectcoders2024.convention.helpers.androidTestImplementation
import com.davidluna.architectcoders2024.convention.helpers.debugImplementation
import com.davidluna.architectcoders2024.convention.helpers.kspAndroidTest
import com.davidluna.architectcoders2024.processed.libs.androidRunner
import com.davidluna.architectcoders2024.processed.libs.androidTestRules
import com.davidluna.architectcoders2024.processed.libs.composeBom
import com.davidluna.architectcoders2024.processed.libs.hiltCompiler
import com.davidluna.architectcoders2024.processed.libs.hiltTest
import com.davidluna.architectcoders2024.processed.libs.libs
import com.davidluna.architectcoders2024.processed.libs.mockWebServer
import com.davidluna.architectcoders2024.processed.libs.navigationTesting
import com.davidluna.architectcoders2024.processed.libs.uiTestJunit4
import com.davidluna.architectcoders2024.processed.libs.uiTestManifest
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal val Project.androidTestingBundle: Unit
    get() {
        dependencies {
            androidTestImplementation(platform(libs.composeBom))
            androidTestImplementation(libs.uiTestJunit4)
            androidTestImplementation(libs.navigationTesting)
            debugImplementation(libs.uiTestManifest)
            androidTestImplementation(libs.androidRunner)
            androidTestImplementation(libs.mockWebServer)
            androidTestImplementation(libs.androidTestRules)
            androidTestImplementation(libs.hiltTest)
            kspAndroidTest(libs.hiltCompiler)
        }
    }