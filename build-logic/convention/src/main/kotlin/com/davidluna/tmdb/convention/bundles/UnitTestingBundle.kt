package com.davidluna.tmdb.convention.bundles

import com.davidluna.tmdb.convention.helpers.testImplementation
import com.davidluna.tmdb.convention.libs.coroutinesTest
import com.davidluna.tmdb.convention.libs.junit
import com.davidluna.tmdb.convention.libs.libs
import com.davidluna.tmdb.convention.libs.mockitoInline
import com.davidluna.tmdb.convention.libs.mockitoKotlin
import com.davidluna.tmdb.convention.libs.truth
import com.davidluna.tmdb.convention.libs.turbine
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal val Project.unitTestingBundle: Unit
    get() {
        dependencies {
            testImplementation(libs.junit)
            testImplementation(libs.mockitoKotlin)
            testImplementation(libs.mockitoInline)
            testImplementation(libs.coroutinesTest)
            testImplementation(libs.turbine)
            testImplementation(libs.truth)
        }
    }

