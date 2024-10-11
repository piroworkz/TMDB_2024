package com.davidluna.tmdb.convention.plugins

import com.android.build.api.dsl.LibraryExtension
import com.davidluna.tmdb.convention.bundles.koinCoreBundle
import com.davidluna.tmdb.convention.bundles.ktorAndroidBundle
import com.davidluna.tmdb.convention.bundles.unitTestingBundle
import com.davidluna.tmdb.convention.constants.Constants
import com.davidluna.tmdb.convention.constants.NAME_SPACE
import com.davidluna.tmdb.convention.extensions.defaultConfig
import com.davidluna.tmdb.convention.extensions.javaVersion
import com.davidluna.tmdb.convention.extensions.kotlin
import com.davidluna.tmdb.convention.extensions.plugins
import com.davidluna.tmdb.convention.extensions.setBuildTypes
import com.davidluna.tmdb.convention.helpers.alias
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.libs.androidLibrary
import com.davidluna.tmdb.convention.libs.arrowCore
import com.davidluna.tmdb.convention.libs.kotlinAndroid
import com.davidluna.tmdb.convention.libs.kotlinCoroutinesCore
import com.davidluna.tmdb.convention.libs.kotlinSerialization
import com.davidluna.tmdb.convention.libs.kotlinxSerializationJson
import com.davidluna.tmdb.convention.libs.ksp
import com.davidluna.tmdb.convention.libs.libs
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.dependencies

class FrameworkModuleConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins {
                alias(libs.androidLibrary)
                alias(libs.kotlinAndroid)
                alias(libs.ksp)
                alias(libs.kotlinSerialization)
            }

            android {
                namespace = NAME_SPACE
                defaultConfig()
                setBuildTypes()
                compileOptions.apply {
                    sourceCompatibility = Constants.JAVA_VERSION
                    targetCompatibility = Constants.JAVA_VERSION
                }
            }

            kotlin {
                jvmToolchain {
                    languageVersion.set(JavaLanguageVersion.of(17))
                }
            }

            javaVersion
            dependencies {
                koinCoreBundle
                ktorAndroidBundle
                implementation(libs.kotlinxSerializationJson)
                implementation(libs.arrowCore)
                implementation(libs.kotlinCoroutinesCore)
                unitTestingBundle
            }
        }
    }

    private fun Project.android(config: Action<LibraryExtension>) =
        (this as ExtensionAware).extensions.configure("android", config)
}


