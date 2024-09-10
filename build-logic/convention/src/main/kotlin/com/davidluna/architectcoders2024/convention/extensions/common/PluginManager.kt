package com.davidluna.architectcoders2024.convention.extensions.common

import com.davidluna.architectcoders2024.convention.helpers.alias
import com.davidluna.architectcoders2024.convention.libs.androidApplication
import com.davidluna.architectcoders2024.convention.libs.androidLibrary
import com.davidluna.architectcoders2024.convention.libs.composeCompiler
import com.davidluna.architectcoders2024.convention.libs.hiltPlugin
import com.davidluna.architectcoders2024.convention.libs.kotlinAndroid
import com.davidluna.architectcoders2024.convention.libs.kotlinSerialization
import com.davidluna.architectcoders2024.convention.libs.ksp
import com.davidluna.architectcoders2024.convention.libs.libs
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager

private val Project.baseAndroidLibraryPluginManager: PluginManager
    get() = pluginManager.apply {
        alias(libs.androidLibrary)
        alias(libs.kotlinAndroid)
    }

private val Project.hiltPluginManager: PluginManager
    get() = pluginManager.apply {
        alias(libs.hiltPlugin)
        alias(libs.ksp)
    }

internal val Project.frameworkPluginManager: PluginManager
    get() = baseAndroidLibraryPluginManager.apply {
        hiltPluginManager
        alias(libs.kotlinSerialization)
    }

internal val Project.uiPluginManager: PluginManager
    get() = frameworkPluginManager.apply {
        hiltPluginManager
        alias(libs.composeCompiler)
        alias(libs.kotlinSerialization)
    }

internal val Project.applicationPluginManager: PluginManager
    get() = hiltPluginManager.apply {
        alias(libs.androidApplication)
        alias(libs.kotlinAndroid)
        alias(libs.composeCompiler)
    }