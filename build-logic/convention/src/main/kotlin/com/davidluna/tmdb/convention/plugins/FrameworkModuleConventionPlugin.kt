package com.davidluna.tmdb.convention.plugins

import com.davidluna.tmdb.convention.bundles.koinCoreBundle
import com.davidluna.tmdb.convention.bundles.unitTestingBundle
import com.davidluna.tmdb.convention.extensions.android_library.androidLibrary
import com.davidluna.tmdb.convention.extensions.common.frameworkPluginManager
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.libs.arrowCore
import com.davidluna.tmdb.convention.libs.kotlinCoroutinesCore
import com.davidluna.tmdb.convention.libs.kotlinxSerializationJson
import com.davidluna.tmdb.convention.libs.libs
import com.davidluna.tmdb.convention.libs.retrofit
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FrameworkModuleConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            frameworkPluginManager
            androidLibrary
            dependencies()
        }
    }


    private fun Project.dependencies() {
        dependencies {
            koinCoreBundle
            implementation(libs.kotlinxSerializationJson)
            implementation(libs.retrofit)
            implementation(libs.arrowCore)
            implementation(libs.kotlinCoroutinesCore)
            unitTestingBundle
        }
    }
}