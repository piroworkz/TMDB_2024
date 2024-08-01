package com.davidluna.architectcoders2024.convention.extensions.common

import com.davidluna.architectcoders2024.convention.helpers.alias
import com.davidluna.architectcoders2024.processed.libs.androidApplication
import com.davidluna.architectcoders2024.processed.libs.androidLibrary
import com.davidluna.architectcoders2024.processed.libs.composeCompiler
import com.davidluna.architectcoders2024.processed.libs.hiltPlugin
import com.davidluna.architectcoders2024.processed.libs.kotlinAndroid
import com.davidluna.architectcoders2024.processed.libs.kotlinSerialization
import com.davidluna.architectcoders2024.processed.libs.ksp
import com.davidluna.architectcoders2024.processed.libs.libs
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
    }

internal val Project.applicationPluginManager: PluginManager
    get() = pluginManager.apply {
        alias(libs.androidApplication)
        alias(libs.kotlinAndroid)
        alias(libs.hiltPlugin)
        alias(libs.ksp)
    }