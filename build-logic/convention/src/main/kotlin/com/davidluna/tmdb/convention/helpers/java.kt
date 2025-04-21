package com.davidluna.tmdb.convention.helpers

import org.gradle.api.*
import org.gradle.api.plugins.*

internal fun Project.java(config: Action<JavaPluginExtension>): Unit =
    (this as ExtensionAware).extensions.configure("java", config)