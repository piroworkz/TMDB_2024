package com.davidluna.architectcoders2024.convention.bundles

import com.davidluna.architectcoders2024.convention.helpers.androidTestImplementation
import com.davidluna.architectcoders2024.convention.helpers.debugImplementation
import com.davidluna.architectcoders2024.convention.helpers.kspAndroidTest
import com.davidluna.architectcoders2024.convention.libs.androidRunner
import com.davidluna.architectcoders2024.convention.libs.androidTestCore
import com.davidluna.architectcoders2024.convention.libs.androidTestRules
import com.davidluna.architectcoders2024.convention.libs.composeBom
import com.davidluna.architectcoders2024.convention.libs.datastoreCore
import com.davidluna.architectcoders2024.convention.libs.espressoIntents
import com.davidluna.architectcoders2024.convention.libs.hiltCompiler
import com.davidluna.architectcoders2024.convention.libs.hiltTest
import com.davidluna.architectcoders2024.convention.libs.libs
import com.davidluna.architectcoders2024.convention.libs.mockWebServer
import com.davidluna.architectcoders2024.convention.libs.navigationTesting
import com.davidluna.architectcoders2024.convention.libs.playServicesLocation
import com.davidluna.architectcoders2024.convention.libs.protobufJavalite
import com.davidluna.architectcoders2024.convention.libs.protobufKotlinLite
import com.davidluna.architectcoders2024.convention.libs.uiTestJunit4
import com.davidluna.architectcoders2024.convention.libs.uiTestManifest
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
            androidTestImplementation(libs.androidTestRules)
            androidTestImplementation(libs.androidTestCore)
            androidTestImplementation(libs.espressoIntents)
            androidTestImplementation(libs.mockWebServer)
            androidTestImplementation(libs.hiltTest)
            kspAndroidTest(libs.hiltCompiler)
            androidTestImplementation(libs.datastoreCore)
            androidTestImplementation(libs.protobufJavalite)
            androidTestImplementation(libs.protobufKotlinLite)
            androidTestImplementation(libs.playServicesLocation)
        }
    }