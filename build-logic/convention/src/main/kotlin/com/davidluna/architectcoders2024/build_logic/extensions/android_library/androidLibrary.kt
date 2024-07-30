package com.davidluna.architectcoders2024.build_logic.extensions.android_library

import com.android.build.api.dsl.CompileOptions
import com.davidluna.architectcoders2024.build_logic.extensions.common.setJavaVersions
import com.davidluna.architectcoders2024.build_logic.extensions.common.setVersions
import com.davidluna.architectcoders2024.build_logic.helpers.androidLibrary
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