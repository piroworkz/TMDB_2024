package com.davidluna.tmdb.convention.plugins

import com.davidluna.tmdb.convention.extensions.javaVersion
import com.davidluna.tmdb.convention.helpers.alias
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.libs.coroutinesTest
import com.davidluna.tmdb.convention.libs.junit
import com.davidluna.tmdb.convention.libs.kotlinJvm
import com.davidluna.tmdb.convention.libs.kotlinxSerializationJson
import com.davidluna.tmdb.convention.libs.libs
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class TestSharedConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                alias(libs.kotlinJvm)
            }
            javaVersion
            kotlin { jvmToolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }
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

    internal fun Project.kotlin(config: Action<KotlinJvmProjectExtension>): Unit =
        (this as ExtensionAware).extensions.configure("kotlin", config)
}