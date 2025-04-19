package com.davidluna.tmdb.convention.plugins
import com.davidluna.tmdb.convention.bundles.unitTestingBundle
import com.davidluna.tmdb.convention.constants.Constants
import com.davidluna.tmdb.convention.helpers.alias
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.helpers.java
import com.davidluna.tmdb.convention.libs.arrowCore
import com.davidluna.tmdb.convention.libs.javaxInject
import com.davidluna.tmdb.convention.libs.kotlinCoroutinesCore
import com.davidluna.tmdb.convention.libs.kotlinJvm
import com.davidluna.tmdb.convention.libs.libs
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