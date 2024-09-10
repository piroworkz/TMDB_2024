package com.davidluna.architectcoders2024.convention.bundles

import com.davidluna.architectcoders2024.convention.helpers.debugImplementation
import com.davidluna.architectcoders2024.convention.helpers.implementation
import com.davidluna.architectcoders2024.convention.libs.composeAnimation
import com.davidluna.architectcoders2024.convention.libs.composeBom
import com.davidluna.architectcoders2024.convention.libs.composeMaterial3
import com.davidluna.architectcoders2024.convention.libs.composeNavigation
import com.davidluna.architectcoders2024.convention.libs.composeUi
import com.davidluna.architectcoders2024.convention.libs.composeUiGraphics
import com.davidluna.architectcoders2024.convention.libs.composeUiTooling
import com.davidluna.architectcoders2024.convention.libs.composeUiToolingPreview
import com.davidluna.architectcoders2024.convention.libs.iconsExtended
import com.davidluna.architectcoders2024.convention.libs.libs
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
            implementation(libs.composeNavigation)
            implementation(libs.composeAnimation)
            implementation(libs.iconsExtended)
            debugImplementation(libs.composeUiTooling)
        }
    }