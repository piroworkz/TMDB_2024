package com.davidluna.tmdb.convention.extensions.android_library

import com.android.build.api.dsl.CompileOptions
import com.davidluna.tmdb.convention.extensions.common.setJavaVersions
import com.davidluna.tmdb.convention.extensions.common.setVersions
import com.davidluna.tmdb.convention.helpers.androidLibrary
import org.gradle.api.Project

internal val Project.androidLibrary: Unit
    get() {
        androidLibrary {
            defaultConfig()
            compileOptions(CompileOptions::setVersions)
            setBuildTypes()
        }
        setJavaVersions()
    }