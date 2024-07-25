package com.davidluna.architectcoders2024.build_logic.plugins

import com.davidluna.architectcoders2024.build_logic.extensions.application.application
import com.davidluna.architectcoders2024.build_logic.extensions.common.applicationPluginManager
import com.davidluna.architectcoders2024.build_logic.libs.helpers.implementation
import com.davidluna.architectcoders2024.build_logic.libs.helpers.ksp
import com.davidluna.architectcoders2024.build_logic.libs.hiltAndroid
import com.davidluna.architectcoders2024.build_logic.libs.hiltCompiler
import com.davidluna.architectcoders2024.build_logic.libs.libs
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
        }
    }


}