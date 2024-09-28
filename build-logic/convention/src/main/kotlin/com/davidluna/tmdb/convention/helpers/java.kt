package com.davidluna.tmdb.convention.helpers

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.JavaPluginExtension

internal fun Project.java(config: Action<JavaPluginExtension>): Unit =
    (this as ExtensionAware).extensions.configure("java", config)