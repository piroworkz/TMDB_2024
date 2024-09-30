package com.davidluna.tmdb.convention.extensions

import com.davidluna.tmdb.convention.constants.Constants
import com.davidluna.tmdb.convention.helpers.java
import org.gradle.api.Project

internal val Project.javaVersion: Unit
    get() = java {
        sourceCompatibility = Constants.JAVA_VERSION
        targetCompatibility = Constants.JAVA_VERSION
    }