package com.davidluna.tmdb.convention.plugins

import com.davidluna.tmdb.convention.bundles.androidTestingBundle
import com.davidluna.tmdb.convention.bundles.composeUiBundle
import com.davidluna.tmdb.convention.bundles.unitTestingBundle
import com.davidluna.tmdb.convention.extensions.application.application
import com.davidluna.tmdb.convention.extensions.common.applicationPluginManager
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.helpers.ksp
import com.davidluna.tmdb.convention.libs.arrowCore
import com.davidluna.tmdb.convention.libs.coilCompose
import com.davidluna.tmdb.convention.libs.composeActivity
import com.davidluna.tmdb.convention.libs.composeNavigation
import com.davidluna.tmdb.convention.libs.hiltAndroid
import com.davidluna.tmdb.convention.libs.hiltCompiler
import com.davidluna.tmdb.convention.libs.hiltNavigationCompose
import com.davidluna.tmdb.convention.libs.kotlinConverter
import com.davidluna.tmdb.convention.libs.kotlinxSerializationJson
import com.davidluna.tmdb.convention.libs.libs
import com.davidluna.tmdb.convention.libs.okhttpClient
import com.davidluna.tmdb.convention.libs.okhttpLoggingInterceptor
import com.davidluna.tmdb.convention.libs.retrofit
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
            implementation(libs.composeNavigation)
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