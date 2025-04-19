package com.davidluna.tmdb.convention.plugins

import com.davidluna.tmdb.convention.bundles.androidTestingBundle
import com.davidluna.tmdb.convention.bundles.composeUiBundle
import com.davidluna.tmdb.convention.bundles.unitTestingBundle
import com.davidluna.tmdb.convention.extensions.android_library.androidLibrary
import com.davidluna.tmdb.convention.extensions.common.uiPluginManager
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.helpers.ksp
import com.davidluna.tmdb.convention.libs.arrowCore
import com.davidluna.tmdb.convention.libs.hiltAndroid
import com.davidluna.tmdb.convention.libs.hiltCompiler
import com.davidluna.tmdb.convention.libs.hiltNavigationCompose
import com.davidluna.tmdb.convention.libs.kotlinxSerializationJson
import com.davidluna.tmdb.convention.libs.libs
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
            implementation(libs.kotlinxSerializationJson)
            ksp(libs.hiltCompiler)
            composeUiBundle
            unitTestingBundle
            androidTestingBundle
        }
    }
}