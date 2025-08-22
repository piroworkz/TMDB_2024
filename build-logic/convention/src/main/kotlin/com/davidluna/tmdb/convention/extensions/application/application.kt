package com.davidluna.tmdb.convention.extensions.application

import com.davidluna.tmdb.convention.extensions.android_library.kotlin
import com.davidluna.tmdb.convention.extensions.common.setJavaVersions
import com.davidluna.tmdb.convention.extensions.common.setVersions
import com.davidluna.tmdb.convention.helpers.androidApplication
import org.gradle.api.Project

internal val Project.application: Unit
    get() {
        androidApplication {
            setDefaultConfig(project)
            setSigningConfig(this)
            setBuildTypes()
            setJavaVersions()
            compileOptions.setVersions()
            buildFeatures {
                buildConfig = true
            }
            packaging {
                resources {
                    excludes.apply {
                        add("/META-INF/{AL2.0,LGPL2.1}")
                        add("META-INF/LICENSE.md")
                        add("META-INF/LICENSE")
                        add("META-INF/NOTICE.md")
                        add("META-INF/NOTICE")
                    }
                }
            }

            kotlin{
                jvmToolchain(17)
            }
        }
    }




