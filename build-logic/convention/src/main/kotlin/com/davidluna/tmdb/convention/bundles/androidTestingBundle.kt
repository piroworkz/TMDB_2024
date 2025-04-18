package com.davidluna.tmdb.convention.bundles

import com.davidluna.tmdb.convention.helpers.androidTestImplementation
import com.davidluna.tmdb.convention.helpers.debugImplementation
import com.davidluna.tmdb.convention.helpers.kspAndroidTest
import com.davidluna.tmdb.convention.libs.androidRunner
import com.davidluna.tmdb.convention.libs.androidTestCore
import com.davidluna.tmdb.convention.libs.androidTestRules
import com.davidluna.tmdb.convention.libs.composeBom
import com.davidluna.tmdb.convention.libs.datastoreCore
import com.davidluna.tmdb.convention.libs.espressoIntents
import com.davidluna.tmdb.convention.libs.hiltCompiler
import com.davidluna.tmdb.convention.libs.hiltTest
import com.davidluna.tmdb.convention.libs.libs
import com.davidluna.tmdb.convention.libs.mockWebServer
import com.davidluna.tmdb.convention.libs.navigationTesting
import com.davidluna.tmdb.convention.libs.playServicesLocation
import com.davidluna.tmdb.convention.libs.protobufJavalite
import com.davidluna.tmdb.convention.libs.protobufKotlinLite
import com.davidluna.tmdb.convention.libs.uiTestJunit4
import com.davidluna.tmdb.convention.libs.uiTestManifest
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