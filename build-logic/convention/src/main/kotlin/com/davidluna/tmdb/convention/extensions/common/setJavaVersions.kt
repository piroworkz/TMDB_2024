package com.davidluna.tmdb.convention.extensions.common

import com.davidluna.tmdb.convention.constants.Constants
import com.davidluna.tmdb.convention.helpers.java
import org.gradle.api.Project

internal fun Project.setJavaVersions(){
    java {
        sourceCompatibility = Constants.JAVA_VERSION
        targetCompatibility = Constants.JAVA_VERSION
    }
}