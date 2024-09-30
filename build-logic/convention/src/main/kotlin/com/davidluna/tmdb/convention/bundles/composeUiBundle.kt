package com.davidluna.tmdb.convention.bundles

import com.davidluna.tmdb.convention.helpers.debugImplementation
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.libs.composeAnimation
import com.davidluna.tmdb.convention.libs.composeBom
import com.davidluna.tmdb.convention.libs.composeMaterial3
import com.davidluna.tmdb.convention.libs.composeNavigation
import com.davidluna.tmdb.convention.libs.composeUi
import com.davidluna.tmdb.convention.libs.composeUiGraphics
import com.davidluna.tmdb.convention.libs.composeUiTooling
import com.davidluna.tmdb.convention.libs.composeUiToolingPreview
import com.davidluna.tmdb.convention.libs.iconsExtended
import com.davidluna.tmdb.convention.libs.koinAndroid
import com.davidluna.tmdb.convention.libs.koinBom
import com.davidluna.tmdb.convention.libs.koinCompose
import com.davidluna.tmdb.convention.libs.koinComposeViewmodelNavigation
import com.davidluna.tmdb.convention.libs.koinCore
import com.davidluna.tmdb.convention.libs.libs
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

internal val Project.koinAppBundle: Unit
    get() {
        dependencies {
            implementation(platform(libs.koinBom))
            implementation(libs.koinAndroid)
            implementation(libs.koinCompose)
            implementation(libs.koinComposeViewmodelNavigation)
        }
    }

internal val Project.koinAndroidBundle: Unit
    get() {
        dependencies {
            implementation(platform(libs.koinBom))
            implementation(libs.koinAndroid)
            implementation(libs.koinCompose)
            implementation(libs.koinComposeViewmodelNavigation)
        }
    }

internal val Project.koinCoreBundle: Unit
    get() {
        dependencies {
            implementation(platform(libs.koinBom))
            implementation(libs.koinCore)
        }
    }