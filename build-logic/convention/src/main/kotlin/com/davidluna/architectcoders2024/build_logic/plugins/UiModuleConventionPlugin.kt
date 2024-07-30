package com.davidluna.architectcoders2024.build_logic.plugins

import com.davidluna.architectcoders2024.build_logic.bundles.composeUiBundle
import com.davidluna.architectcoders2024.build_logic.bundles.unitTestingBundle
import com.davidluna.architectcoders2024.build_logic.extensions.android_library.androidLibrary
import com.davidluna.architectcoders2024.build_logic.extensions.common.uiPluginManager
import com.davidluna.architectcoders2024.build_logic.libs.arrowCore
import com.davidluna.architectcoders2024.build_logic.helpers.implementation
import com.davidluna.architectcoders2024.build_logic.helpers.ksp
import com.davidluna.architectcoders2024.build_logic.libs.hiltAndroid
import com.davidluna.architectcoders2024.build_logic.libs.hiltCompiler
import com.davidluna.architectcoders2024.build_logic.libs.hiltNavigationCompose
import com.davidluna.architectcoders2024.build_logic.libs.libs
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
        }
    }
}