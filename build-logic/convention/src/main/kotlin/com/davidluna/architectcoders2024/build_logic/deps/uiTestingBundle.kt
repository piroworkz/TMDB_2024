package com.davidluna.architectcoders2024.build_logic.deps

import com.davidluna.architectcoders2024.build_logic.dependency_utilities.androidTestImplementation
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.kaptAndroidTest
import com.davidluna.architectcoders2024.build_logic.libs.androidRunner
import com.davidluna.architectcoders2024.build_logic.libs.composeBom
import com.davidluna.architectcoders2024.build_logic.libs.hiltCompiler
import com.davidluna.architectcoders2024.build_logic.libs.hiltTest
import com.davidluna.architectcoders2024.build_logic.libs.libs
import com.davidluna.architectcoders2024.build_logic.libs.mockWebServer
import com.davidluna.architectcoders2024.build_logic.libs.navigationTesting
import com.davidluna.architectcoders2024.build_logic.libs.uiTestJunit4
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.uiTestingBundle() {
    dependencies {
        androidTestImplementation(platform(libs.composeBom))
        androidTestImplementation(libs.uiTestJunit4)
        androidTestImplementation(libs.navigationTesting)
        androidTestImplementation(libs.androidRunner)
        androidTestImplementation(libs.mockWebServer)
        androidTestImplementation(libs.hiltTest)
        kaptAndroidTest(libs.hiltCompiler)
    }
}