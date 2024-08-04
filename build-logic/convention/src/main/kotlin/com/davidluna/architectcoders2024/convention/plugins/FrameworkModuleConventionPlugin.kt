package com.davidluna.architectcoders2024.convention.plugins

import com.davidluna.architectcoders2024.convention.bundles.unitTestingBundle
import com.davidluna.architectcoders2024.convention.extensions.android_library.androidLibrary
import com.davidluna.architectcoders2024.convention.extensions.common.frameworkPluginManager
import com.davidluna.architectcoders2024.convention.helpers.implementation
import com.davidluna.architectcoders2024.convention.helpers.ksp
import com.davidluna.architectcoders2024.convention.libs.arrowCore
import com.davidluna.architectcoders2024.convention.libs.hiltAndroid
import com.davidluna.architectcoders2024.convention.libs.hiltCompiler
import com.davidluna.architectcoders2024.convention.libs.javaxInject
import com.davidluna.architectcoders2024.convention.libs.kotlinCoroutinesCore
import com.davidluna.architectcoders2024.convention.libs.kotlinxSerializationJson
import com.davidluna.architectcoders2024.convention.libs.libs
import com.davidluna.architectcoders2024.convention.libs.retrofit
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
            implementation(libs.kotlinxSerializationJson)
            implementation(libs.retrofit)
            implementation(libs.arrowCore)
            implementation(libs.kotlinCoroutinesCore)
            implementation(libs.hiltAndroid)
            ksp(libs.hiltCompiler)
            implementation(libs.javaxInject)
            unitTestingBundle
        }
    }
}