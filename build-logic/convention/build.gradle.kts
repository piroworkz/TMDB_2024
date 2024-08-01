plugins {
    `kotlin-dsl`
    alias(libs.plugins.ksp)
}

group = "com.davidluna.architectcoders2024"

dependencies {
    compileOnly(libs.androidGradlePlugin)
    compileOnly(libs.kotlinGradlePlugin)
    ksp(libs.piroworkzVersionsCatalog)
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

ksp {
    arg("libsPath", "${rootDir.parentFile}/gradle/libs.versions.toml")
    arg("packageName", "com.davidluna.architectcoders2024.processed.libs")
}

gradlePlugin {

    plugins {
        register("androidAppPlugin") {
            id = "architectcoders2024.android.application"
            implementationClass =
                "com.davidluna.architectcoders2024.convention.plugins.AndroidApplicationConventionPlugin"
        }

        register("frameworkModuleConventionPlugin") {
            id = "architectcoders2024.framework.module.plugin"
            implementationClass =
                "com.davidluna.architectcoders2024.convention.plugins.FrameworkModuleConventionPlugin"
        }

        register("uiModuleConventionPlugin") {
            id = "architectcoders2024.ui.module.plugin"
            implementationClass =
                "com.davidluna.architectcoders2024.convention.plugins.UiModuleConventionPlugin"
        }

        register("kotlinModuleConventionPlugin") {
            id = "architectcoders2024.kotlin.module.plugin"
            implementationClass =
                "com.davidluna.architectcoders2024.convention.plugins.KotlinModuleConventionPlugin"
        }

        register("testSharedConventionPlugin") {
            id = "architectcoders2024.test.shared.plugin"
            implementationClass =
                "com.davidluna.architectcoders2024.convention.plugins.TestSharedConventionPlugin"
        }
    }
}

