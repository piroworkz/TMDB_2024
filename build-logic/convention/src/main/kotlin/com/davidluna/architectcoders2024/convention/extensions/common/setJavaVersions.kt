package com.davidluna.architectcoders2024.convention.extensions.common

import com.davidluna.architectcoders2024.convention.constants.Constants
import com.davidluna.architectcoders2024.convention.helpers.java
import org.gradle.api.Project

internal fun Project.setJavaVersions(){
    java {
        sourceCompatibility = Constants.JAVA_VERSION
        targetCompatibility = Constants.JAVA_VERSION
    }
}