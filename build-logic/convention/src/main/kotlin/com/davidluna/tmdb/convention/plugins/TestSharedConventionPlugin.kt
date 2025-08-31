package com.davidluna.tmdb.convention.plugins

import com.davidluna.tmdb.convention.extensions.common.setJavaVersions
import com.davidluna.tmdb.convention.helpers.alias
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.libs.coroutinesTest
import com.davidluna.tmdb.convention.libs.junit
import com.davidluna.tmdb.convention.libs.kotlinJvm
import com.davidluna.tmdb.convention.libs.kotlinSerialization
import com.davidluna.tmdb.convention.libs.kotlinxSerializationJson
import com.davidluna.tmdb.convention.libs.libs
import com.davidluna.tmdb.convention.libs.mockk
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension

@Suppress("unused")
class TestSharedConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                alias(libs.kotlinJvm)
                alias(libs.kotlinSerialization)
            }
            setJavaVersions()
            setKotlinJvmToolchain()
            dependencies()
        }
    }

    private fun Project.setKotlinJvmToolchain() {
        extensions.configure(KotlinJvmExtension::class.java) {
            jvmToolchain(17)
        }
    }

    private fun Project.dependencies() {
        dependencies {
            implementation(libs.kotlinxSerializationJson)
            implementation(libs.coroutinesTest)
            implementation(libs.junit)
            implementation(libs.mockk)
        }
    }
}