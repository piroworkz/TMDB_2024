@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}





dependencyResolutionManagement {

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("appConfig") {
            from(files("gradle/appConfig.versions.toml"))
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "ArchitectCoders2024"
include(
    ":app",
    ":feature:main:main_ui",
    ":feature:splash_ui",
    ":feature:auth:auth_ui",
    ":feature:auth:auth_domain",
    ":feature:auth:auth_data:auth_data_repositories",
    ":feature:auth:auth_data:auth_data_framework",
    ":feature:media:media_ui",
    ":feature:media:media_domain",
    ":feature:media:media_data:media_data_framework",
    ":feature:media:media_data:media_data_repositories",
    ":feature:videos:videos_ui",
    ":feature:videos:videos_domain",
    ":feature:videos:videos_data:videos_data_framework",
    ":feature:videos:videos_data:videos_data_repositories",
    ":core:core_ui",
    ":core:core_domain",
    ":core:core_data:core_data_framework",
    ":core:core_data:core_data_repositories",
    ":di",
    ":navigation"
)