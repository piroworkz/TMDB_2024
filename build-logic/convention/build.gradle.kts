import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "architectcoders2024.build-logic"

dependencies {
    compileOnly(libs.androidGradlePlugin)
    compileOnly(libs.kotlinGradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = JvmTarget.JVM_17.target
}

gradlePlugin {

    plugins {
        register("androidAppPlugin") {
            id = "architectcoders2024.android.application"
            implementationClass = "com.davidluna.architectcoders2024.build_logic.plugins.AndroidApplicationConventionPlugin"
        }

        register("frameworkModuleConventionPlugin") {
            id = "architectcoders2024.framework.module.plugin"
            implementationClass = "com.davidluna.architectcoders2024.build_logic.plugins.FrameworkModuleConventionPlugin"
        }

        register("uiModuleConventionPlugin") {
            id = "architectcoders2024.ui.module.plugin"
            implementationClass = "com.davidluna.architectcoders2024.build_logic.plugins.UiModuleConventionPlugin"
        }

        register("kotlinModuleConventionPlugin") {
            id = "architectcoders2024.kotlin.module.plugin"
            implementationClass = "com.davidluna.architectcoders2024.build_logic.plugins.KotlinModuleConventionPlugin"
        }

        register("testSharedConventionPlugin") {
            id = "architectcoders2024.test.shared.plugin"
            implementationClass = "com.davidluna.architectcoders2024.build_logic.plugins.TestSharedConventionPlugin"
        }
    }
}