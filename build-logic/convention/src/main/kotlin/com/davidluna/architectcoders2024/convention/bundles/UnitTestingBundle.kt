package com.davidluna.architectcoders2024.convention.bundles

import com.davidluna.architectcoders2024.convention.helpers.testImplementation
import com.davidluna.architectcoders2024.processed.libs.coroutinesTest
import com.davidluna.architectcoders2024.processed.libs.junit
import com.davidluna.architectcoders2024.processed.libs.libs
import com.davidluna.architectcoders2024.processed.libs.mockitoInline
import com.davidluna.architectcoders2024.processed.libs.mockitoKotlin
import com.davidluna.architectcoders2024.processed.libs.truth
import com.davidluna.architectcoders2024.processed.libs.turbine
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

