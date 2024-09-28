package com.davidluna.tmdb.convention.plugins

import com.davidluna.tmdb.convention.extensions.common.setJavaVersions
import com.davidluna.tmdb.convention.helpers.alias
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.libs.coroutinesTest
import com.davidluna.tmdb.convention.libs.junit
import com.davidluna.tmdb.convention.libs.kotlinJvm
import com.davidluna.tmdb.convention.libs.kotlinxSerializationJson
import com.davidluna.tmdb.convention.libs.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestSharedConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                alias(libs.kotlinJvm)
            }
            setJavaVersions()
            dependencies()
        }
    }

    private fun Project.dependencies() {
        dependencies {
            implementation(libs.kotlinxSerializationJson)
            implementation(libs.coroutinesTest)
            implementation(libs.junit)
        }
    }
}