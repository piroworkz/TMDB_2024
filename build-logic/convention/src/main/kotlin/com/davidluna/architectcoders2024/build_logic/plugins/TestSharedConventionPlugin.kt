package com.davidluna.architectcoders2024.build_logic.plugins

import com.davidluna.architectcoders2024.build_logic.extensions.common.setJavaVersions
import com.davidluna.architectcoders2024.build_logic.libs.coroutinesTest
import com.davidluna.architectcoders2024.build_logic.helpers.alias
import com.davidluna.architectcoders2024.build_logic.helpers.implementation
import com.davidluna.architectcoders2024.build_logic.libs.junit
import com.davidluna.architectcoders2024.build_logic.libs.kotlinJvm
import com.davidluna.architectcoders2024.build_logic.libs.kotlinxSerializationJson
import com.davidluna.architectcoders2024.build_logic.libs.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestSharedConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                alias(libs.kotlinJvm)
            }
            setJavaVersions()
            dependencies()
        }
    }

    private fun Project.dependencies() {
        dependencies {
            implementation(libs.kotlinxSerializationJson)
            implementation(libs.coroutinesTest)
            implementation(libs.junit)
        }
    }
}