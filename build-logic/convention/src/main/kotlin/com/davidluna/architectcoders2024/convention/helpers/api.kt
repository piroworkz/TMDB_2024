package com.davidluna.architectcoders2024.convention.helpers

import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.api(dependencyNotation: Any): Dependency? {
    return add("api", dependencyNotation)
}