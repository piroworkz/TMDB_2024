package com.davidluna.architectcoders2024.build_logic.libs.helpers

import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.ksp(dependencyNotation: Any): Dependency? {
    return add("ksp", dependencyNotation)
}