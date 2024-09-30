package com.davidluna.tmdb.convention.extensions

import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager

fun Project.plugins(config: PluginManager.() -> Unit): PluginManager =
    pluginManager.apply(config)