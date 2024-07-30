package com.davidluna.architectcoders2024.build_logic.bundles

import com.davidluna.architectcoders2024.build_logic.libs.composeAnimation
import com.davidluna.architectcoders2024.build_logic.libs.composeBom
import com.davidluna.architectcoders2024.build_logic.libs.composeMaterial3
import com.davidluna.architectcoders2024.build_logic.libs.composeUi
import com.davidluna.architectcoders2024.build_logic.libs.composeUiGraphics
import com.davidluna.architectcoders2024.build_logic.libs.composeUiTooling
import com.davidluna.architectcoders2024.build_logic.libs.composeUiToolingPreview
import com.davidluna.architectcoders2024.build_logic.helpers.debugImplementation
import com.davidluna.architectcoders2024.build_logic.helpers.implementation
import com.davidluna.architectcoders2024.build_logic.libs.iconsExtended
import com.davidluna.architectcoders2024.build_logic.libs.libs
import com.davidluna.architectcoders2024.build_logic.libs.runtimeTracing
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