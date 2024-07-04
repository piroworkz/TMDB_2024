package com.davidluna.architectcoders2024.build_logic.dependency_utilities

import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any): Dependency? {
    return add("implementation", dependencyNotation)
}
