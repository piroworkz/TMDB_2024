package com.davidluna.tmdb.convention.extensions.common

import com.davidluna.tmdb.convention.helpers.alias
import com.davidluna.tmdb.convention.libs.androidApplication
import com.davidluna.tmdb.convention.libs.androidLibrary
import com.davidluna.tmdb.convention.libs.composeCompiler
import com.davidluna.tmdb.convention.libs.googleServices
import com.davidluna.tmdb.convention.libs.hiltPlugin
import com.davidluna.tmdb.convention.libs.kotlinAndroid
import com.davidluna.tmdb.convention.libs.kotlinSerialization
import com.davidluna.tmdb.convention.libs.ksp
import com.davidluna.tmdb.convention.libs.libs
import com.davidluna.tmdb.convention.libs.roomPlugin
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
        alias(libs.composeCompiler)
    }

internal val Project.applicationPluginManager: PluginManager
    get() = hiltPluginManager.apply {
        alias(libs.androidApplication)
        alias(libs.kotlinAndroid)
        alias(libs.composeCompiler)
        alias(libs.googleServices)
    }

internal val Project.roomPluginManger: PluginManager
    get() = frameworkPluginManager.apply {
        alias(libs.roomPlugin)
    }
