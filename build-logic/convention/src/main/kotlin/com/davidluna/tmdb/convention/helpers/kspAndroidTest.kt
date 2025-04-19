package com.davidluna.tmdb.convention.helpers

import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope


internal fun DependencyHandlerScope.kspAndroidTest(dependencyNotation: Any): Dependency? {
    return add("kspAndroidTest", dependencyNotation)
}
