package com.davidluna.architectcoders2024.convention.bundles

import com.davidluna.architectcoders2024.convention.helpers.debugImplementation
import com.davidluna.architectcoders2024.convention.helpers.implementation
import com.davidluna.architectcoders2024.processed.libs.composeAnimation
import com.davidluna.architectcoders2024.processed.libs.composeBom
import com.davidluna.architectcoders2024.processed.libs.composeMaterial3
import com.davidluna.architectcoders2024.processed.libs.composeUi
import com.davidluna.architectcoders2024.processed.libs.composeUiGraphics
import com.davidluna.architectcoders2024.processed.libs.composeUiTooling
import com.davidluna.architectcoders2024.processed.libs.composeUiToolingPreview
import com.davidluna.architectcoders2024.processed.libs.iconsExtended
import com.davidluna.architectcoders2024.processed.libs.libs
import com.davidluna.architectcoders2024.processed.libs.runtimeTracing
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal val Project.composeUiBundle: Unit
    get() {
        dependencies {
            implementation(platform(libs.composeBom))
            implementation(libs.composeUi)
            implementation(libs.composeUiGraphics)
            implementation(libs.composeUiToolingPreview)
            implementation(libs.composeMaterial3)
            implementation(libs.iconsExtended)
            implementation(libs.composeAnimation)
            debugImplementation(libs.runtimeTracing)
            debugImplementation(libs.composeUiTooling)
        }
    }