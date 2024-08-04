package com.davidluna.architectcoders2024.convention.plugins

import com.davidluna.architectcoders2024.convention.bundles.androidTestingBundle
import com.davidluna.architectcoders2024.convention.bundles.composeUiBundle
import com.davidluna.architectcoders2024.convention.bundles.unitTestingBundle
import com.davidluna.architectcoders2024.convention.extensions.android_library.androidLibrary
import com.davidluna.architectcoders2024.convention.extensions.common.uiPluginManager
import com.davidluna.architectcoders2024.convention.helpers.implementation
import com.davidluna.architectcoders2024.convention.helpers.ksp
import com.davidluna.architectcoders2024.processed.libs.arrowCore
import com.davidluna.architectcoders2024.processed.libs.hiltAndroid
import com.davidluna.architectcoders2024.processed.libs.hiltCompiler
import com.davidluna.architectcoders2024.processed.libs.hiltNavigationCompose
import com.davidluna.architectcoders2024.processed.libs.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class UiModuleConventionPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        uiPluginManager
        androidLibrary
        dependencies()
    }

    private fun Project.dependencies() {
        dependencies {
            implementation(libs.arrowCore)
            implementation(libs.hiltNavigationCompose)
            implementation(libs.hiltAndroid)
            ksp(libs.hiltCompiler)
            composeUiBundle
            unitTestingBundle
            androidTestingBundle
        }
    }
}