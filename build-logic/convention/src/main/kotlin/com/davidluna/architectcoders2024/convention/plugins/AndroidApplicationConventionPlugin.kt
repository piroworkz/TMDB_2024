package com.davidluna.architectcoders2024.convention.plugins

import com.davidluna.architectcoders2024.convention.bundles.androidTestingBundle
import com.davidluna.architectcoders2024.convention.bundles.composeUiBundle
import com.davidluna.architectcoders2024.convention.bundles.unitTestingBundle
import com.davidluna.architectcoders2024.convention.extensions.application.application
import com.davidluna.architectcoders2024.convention.extensions.common.applicationPluginManager
import com.davidluna.architectcoders2024.convention.helpers.implementation
import com.davidluna.architectcoders2024.convention.helpers.ksp
import com.davidluna.architectcoders2024.convention.libs.arrowCore
import com.davidluna.architectcoders2024.convention.libs.coilCompose
import com.davidluna.architectcoders2024.convention.libs.composeActivity
import com.davidluna.architectcoders2024.convention.libs.hiltAndroid
import com.davidluna.architectcoders2024.convention.libs.hiltCompiler
import com.davidluna.architectcoders2024.convention.libs.hiltNavigationCompose
import com.davidluna.architectcoders2024.convention.libs.kotlinConverter
import com.davidluna.architectcoders2024.convention.libs.kotlinxSerializationJson
import com.davidluna.architectcoders2024.convention.libs.libs
import com.davidluna.architectcoders2024.convention.libs.navigation
import com.davidluna.architectcoders2024.convention.libs.okhttpClient
import com.davidluna.architectcoders2024.convention.libs.okhttpLoggingInterceptor
import com.davidluna.architectcoders2024.convention.libs.retrofit
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