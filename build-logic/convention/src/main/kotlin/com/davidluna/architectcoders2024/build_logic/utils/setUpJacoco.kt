package com.davidluna.architectcoders2024.build_logic.utils

import org.gradle.api.Project

fun Project.setUpJacoco() {
    jacoco {
        toolVersion = "0.8.11"
    }
}