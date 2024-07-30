package com.davidluna.architectcoders2024.build_logic.plugins

import com.davidluna.architectcoders2024.build_logic.bundles.unitTestingBundle
import com.davidluna.architectcoders2024.build_logic.extensions.android_library.androidLibrary
import com.davidluna.architectcoders2024.build_logic.extensions.common.frameworkPluginManager
import com.davidluna.architectcoders2024.build_logic.libs.arrowCore
import com.davidluna.architectcoders2024.build_logic.helpers.implementation
import com.davidluna.architectcoders2024.build_logic.helpers.ksp
import com.davidluna.architectcoders2024.build_logic.libs.hiltAndroid
import com.davidluna.architectcoders2024.build_logic.libs.hiltCompiler
import com.davidluna.architectcoders2024.build_logic.libs.javaxInject
import com.davidluna.architectcoders2024.build_logic.libs.kotlinCoroutinesCore
import com.davidluna.architectcoders2024.build_logic.libs.kotlinxSerializationJson
import com.davidluna.architectcoders2024.build_logic.libs.libs
import com.davidluna.architectcoders2024.build_logic.libs.retrofit
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