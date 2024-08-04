package com.davidluna.architectcoders2024.convention.plugins

import com.davidluna.architectcoders2024.convention.bundles.androidTestingBundle
import com.davidluna.architectcoders2024.convention.bundles.composeUiBundle
import com.davidluna.architectcoders2024.convention.bundles.unitTestingBundle
import com.davidluna.architectcoders2024.convention.extensions.application.application
import com.davidluna.architectcoders2024.convention.extensions.common.applicationPluginManager
import com.davidluna.architectcoders2024.convention.helpers.implementation
import com.davidluna.architectcoders2024.convention.helpers.ksp
import com.davidluna.architectcoders2024.processed.libs.arrowCore
import com.davidluna.architectcoders2024.processed.libs.coilCompose
import com.davidluna.architectcoders2024.processed.libs.composeActivity
import com.davidluna.architectcoders2024.processed.libs.hiltAndroid
import com.davidluna.architectcoders2024.processed.libs.hiltCompiler
import com.davidluna.architectcoders2024.processed.libs.hiltNavigationCompose
import com.davidluna.architectcoders2024.processed.libs.kotlinConverter
import com.davidluna.architectcoders2024.processed.libs.kotlinxSerializationJson
import com.davidluna.architectcoders2024.processed.libs.libs
import com.davidluna.architectcoders2024.processed.libs.navigation
import com.davidluna.architectcoders2024.processed.libs.okhttpClient
import com.davidluna.architectcoders2024.processed.libs.okhttpLoggingInterceptor
import com.davidluna.architectcoders2024.processed.libs.retrofit
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        applicationPluginManager
        application
        setDependencies()
    }

    private fun Project.setDependencies() {
        dependencies {
            composeUiBundle
            implementation(libs.arrowCore)
            implementation(libs.hiltNavigationCompose)
            implementation(libs.composeActivity)
            implementation(libs.coilCompose)
            implementation(libs.navigation)
            implementation(libs.retrofit)
            implementation(libs.okhttpClient)
            implementation(libs.okhttpLoggingInterceptor)
            implementation(libs.kotlinxSerializationJson)
            implementation(libs.kotlinConverter)
            implementation(libs.hiltAndroid)
            ksp(libs.hiltCompiler)
            unitTestingBundle
            androidTestingBundle
        }
    }

}