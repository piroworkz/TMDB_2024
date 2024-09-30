package com.davidluna.tmdb.convention.plugins

import com.davidluna.tmdb.convention.bundles.unitTestingBundle
import com.davidluna.tmdb.convention.extensions.javaVersion
import com.davidluna.tmdb.convention.extensions.plugins
import com.davidluna.tmdb.convention.helpers.alias
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.libs.arrowCore
import com.davidluna.tmdb.convention.libs.koinBom
import com.davidluna.tmdb.convention.libs.koinCore
import com.davidluna.tmdb.convention.libs.kotlinCoroutinesCore
import com.davidluna.tmdb.convention.libs.kotlinJvm
import com.davidluna.tmdb.convention.libs.libs
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class KotlinModuleConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {

        plugins {
            alias(libs.kotlinJvm)
        }

        javaVersion

        kotlin {
            jvmToolchain {
                languageVersion.set(JavaLanguageVersion.of(17))
            }
        }

        dependencies {
            implementation(libs.kotlinCoroutinesCore)
            implementation(libs.arrowCore)
            implementation(platform(libs.koinBom))
            implementation(libs.koinCore)
            unitTestingBundle
        }
    }

    internal fun Project.kotlin(config: Action<KotlinJvmProjectExtension>): Unit =
        (this as ExtensionAware).extensions.configure("kotlin", config)
}



