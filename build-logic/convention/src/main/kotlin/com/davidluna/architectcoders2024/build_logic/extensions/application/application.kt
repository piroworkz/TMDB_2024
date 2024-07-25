package com.davidluna.architectcoders2024.build_logic.extensions.application

import com.davidluna.architectcoders2024.build_logic.extensions.common.setJavaVersions
import com.davidluna.architectcoders2024.build_logic.extensions.common.setVersions
import com.davidluna.architectcoders2024.build_logic.libs.helpers.androidApplication
import org.gradle.api.Project

internal val Project.application: Unit
    get() {
        androidApplication {
            setDefaultConfig()
            setBuildTypes()
            setJavaVersions()
            compileOptions.setVersions()

            buildFeatures {
                buildConfig = true
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
        }
    }


