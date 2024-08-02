package com.davidluna.architectcoders2024.convention.plugins

import com.davidluna.architectcoders2024.convention.bundles.androidTestingBundle
import com.davidluna.architectcoders2024.convention.extensions.application.application
import com.davidluna.architectcoders2024.convention.extensions.common.applicationPluginManager
import com.davidluna.architectcoders2024.convention.helpers.implementation
import com.davidluna.architectcoders2024.convention.helpers.ksp
import com.davidluna.architectcoders2024.processed.libs.hiltAndroid
import com.davidluna.architectcoders2024.processed.libs.hiltCompiler
import com.davidluna.architectcoders2024.processed.libs.libs
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
            implementation(libs.hiltAndroid)
            ksp(libs.hiltCompiler)
            androidTestingBundle
        }
    }

}