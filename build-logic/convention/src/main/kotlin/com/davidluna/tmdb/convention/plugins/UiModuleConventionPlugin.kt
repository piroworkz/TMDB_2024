package com.davidluna.tmdb.convention.plugins

import com.davidluna.tmdb.convention.bundles.androidTestingBundle
import com.davidluna.tmdb.convention.bundles.composeUiBundle
import com.davidluna.tmdb.convention.bundles.koinAndroidBundle
import com.davidluna.tmdb.convention.bundles.unitTestingBundle
import com.davidluna.tmdb.convention.extensions.android_library.androidLibrary
import com.davidluna.tmdb.convention.extensions.common.uiPluginManager
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.libs.arrowCore
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
            implementation(libs.kotlinxSerializationJson)
            composeUiBundle
            koinAndroidBundle
            unitTestingBundle
            androidTestingBundle
        }
    }
}