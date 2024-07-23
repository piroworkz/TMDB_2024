package com.davidluna.architectcoders2024.build_logic.utils

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

internal fun Project.`android`(config: Action<LibraryExtension>): Unit =
    (this as ExtensionAware).extensions.configure("android", config)