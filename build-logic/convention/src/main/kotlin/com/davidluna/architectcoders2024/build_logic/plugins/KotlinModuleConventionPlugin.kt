package com.davidluna.architectcoders2024.build_logic.plugins
import com.davidluna.architectcoders2024.build_logic.bundles.unitTestingBundle
import com.davidluna.architectcoders2024.build_logic.constants.Constants
import com.davidluna.architectcoders2024.build_logic.libs.arrowCore
import com.davidluna.architectcoders2024.build_logic.helpers.alias
import com.davidluna.architectcoders2024.build_logic.helpers.implementation
import com.davidluna.architectcoders2024.build_logic.helpers.java
import com.davidluna.architectcoders2024.build_logic.libs.javaxInject
import com.davidluna.architectcoders2024.build_logic.libs.kotlinCoroutinesCore
import com.davidluna.architectcoders2024.build_logic.libs.kotlinJvm
import com.davidluna.architectcoders2024.build_logic.libs.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinModuleConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        applyPlugins()
        setUpJavaVersions()
        setupDependencies()
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            alias(libs.kotlinJvm)
        }
    }

    private fun Project.setUpJavaVersions() {
        java {
            sourceCompatibility = Constants.JAVA_VERSION
            targetCompatibility = Constants.JAVA_VERSION
        }
    }

    private fun Project.setupDependencies() {
        dependencies {
            implementation(libs.kotlinCoroutinesCore)
            implementation(libs.arrowCore)
            implementation(libs.javaxInject)
            unitTestingBundle
        }
    }

}