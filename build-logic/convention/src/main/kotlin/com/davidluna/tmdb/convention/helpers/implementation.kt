package com.davidluna.tmdb.convention.helpers

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any): Dependency? {
    return add("implementation", dependencyNotation)
}

internal fun DependencyHandler.project(path: String): Dependency? {
    return add("project", path)
}