@file:Suppress("UnstableApiUsage")

include(":feature:application")


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

rootProject.name = "Tmdb2024"

include(
    ":app",
    ":feature:auth:auth_ui",
    ":feature:auth:auth_domain",
    ":feature:auth:auth_data",
    ":feature:media:media_ui",
    ":feature:media:media_domain",
    ":feature:media:media_data",
    ":core:core_ui",
    ":core:core_domain",
    ":core:core_data",
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
