package com.davidluna.tmdb.convention.extensions.common

import com.davidluna.tmdb.convention.helpers.alias
import com.davidluna.tmdb.convention.libs.androidApplication
import com.davidluna.tmdb.convention.libs.androidLibrary
import com.davidluna.tmdb.convention.libs.composeCompiler
import com.davidluna.tmdb.convention.libs.kotlinAndroid
import com.davidluna.tmdb.convention.libs.kotlinSerialization
import com.davidluna.tmdb.convention.libs.ksp
import com.davidluna.tmdb.convention.libs.libs
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager

internal val Project.frameworkPluginManager: PluginManager
    get() = pluginManager.apply {
        alias(libs.androidLibrary)
        alias(libs.kotlinAndroid)
        alias(libs.ksp)
        alias(libs.kotlinSerialization)
    }

internal val Project.uiPluginManager: PluginManager
    get() = frameworkPluginManager.apply {
        alias(libs.composeCompiler)
    }

internal val Project.applicationPluginManager: PluginManager
    get() = pluginManager.apply {
        alias(libs.ksp)
        alias(libs.androidApplication)
        alias(libs.kotlinAndroid)
        alias(libs.composeCompiler)
    }