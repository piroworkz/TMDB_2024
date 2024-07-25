package com.davidluna.architectcoders2024.build_logic.extensions.common

import com.davidluna.architectcoders2024.build_logic.constants.Constants
import com.davidluna.architectcoders2024.build_logic.libs.helpers.java
import org.gradle.api.Project

internal fun Project.setJavaVersions(){
    java {
        sourceCompatibility = Constants.JAVA_VERSION
        targetCompatibility = Constants.JAVA_VERSION
    }
}