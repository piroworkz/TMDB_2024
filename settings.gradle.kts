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
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Tmdb2024"

include(
    ":app",
    ":feature:splash",
    ":feature:auth:auth_ui",
    ":feature:auth:auth_domain",
    ":feature:auth:auth_framework",
    ":feature:media:media_ui",
    ":feature:media:media_domain",
    ":feature:media:media_framework",
    ":feature:videos:videos_ui",
    ":feature:videos:videos_domain",
    ":feature:videos:videos_framework",
    ":core:core_ui",
    ":core:core_domain",
    ":core:core_framework",
    ":test_shared",
)

rootProject.children.forEach { levelOne ->
    levelOne.buildFileName = "${levelOne.name}.gradle.kts"
    levelOne.children.forEach { levelTwo ->
        levelTwo.buildFileName = "${levelTwo.name}.gradle.kts"
        levelTwo.children.forEach { levelThree: ProjectDescriptor ->
            levelThree.buildFileName = "${levelThree.name}.gradle.kts"
            levelThree.children.forEach { levelFour: ProjectDescriptor ->
                levelFour.buildFileName = "${levelFour.name}.gradle.kts"
            }
        }
    }
}
