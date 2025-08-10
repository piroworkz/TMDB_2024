package com.davidluna.tmdb.convention.extensions.android_library

import com.davidluna.tmdb.convention.extensions.common.setJavaVersions
import com.davidluna.tmdb.convention.extensions.common.setVersions
import com.davidluna.tmdb.convention.helpers.androidLibrary
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal val Project.androidLibrary: Unit
    get() {
        androidLibrary {
            defaultConfig()
            setBuildTypes()
            compileOptions { setVersions() }
        }
        setJavaVersions()
        kotlin {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
                freeCompilerArgs.add("-Xcontext-sensitive-resolution")
            }
        }
    }

internal fun Project.kotlin(config: Action<KotlinAndroidProjectExtension>) {
    (this as ExtensionAware).extensions.configure("kotlin", config)
}