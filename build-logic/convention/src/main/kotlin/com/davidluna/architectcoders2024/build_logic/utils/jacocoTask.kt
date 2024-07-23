package com.davidluna.architectcoders2024.build_logic.utils

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension

internal fun Project.`jacocoTask`(config: Action<JacocoTaskExtension>): Unit =
    (this as ExtensionAware).extensions.configure("jacoco", config)