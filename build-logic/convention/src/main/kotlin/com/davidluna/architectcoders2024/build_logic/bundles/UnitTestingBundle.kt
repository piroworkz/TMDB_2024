package com.davidluna.architectcoders2024.build_logic.bundles

import com.davidluna.architectcoders2024.build_logic.libs.coroutinesTest
import com.davidluna.architectcoders2024.build_logic.libs.helpers.testImplementation
import com.davidluna.architectcoders2024.build_logic.libs.junit
import com.davidluna.architectcoders2024.build_logic.libs.libs
import com.davidluna.architectcoders2024.build_logic.libs.mockitoInline
import com.davidluna.architectcoders2024.build_logic.libs.mockitoKotlin
import com.davidluna.architectcoders2024.build_logic.libs.truth
import com.davidluna.architectcoders2024.build_logic.libs.turbine
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

val Project.unitTestingBundle: Unit
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