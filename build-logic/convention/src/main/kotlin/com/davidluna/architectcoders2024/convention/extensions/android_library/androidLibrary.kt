package com.davidluna.architectcoders2024.convention.extensions.android_library

import com.android.build.api.dsl.CompileOptions
import com.davidluna.architectcoders2024.convention.extensions.common.setJavaVersions
import com.davidluna.architectcoders2024.convention.extensions.common.setVersions
import com.davidluna.architectcoders2024.convention.helpers.androidLibrary
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