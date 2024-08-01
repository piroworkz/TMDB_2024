plugins {
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.protobufPlugin) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hiltPlugin) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeCompiler) apply false
    jacoco
}

subprojects {
    apply(plugin = "jacoco")
    jacoco {
        toolVersion = "0.8.11"
        reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
    }
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks["testDebugUnitTest"])

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*",
        "**/Hilt_*.*",
        "**/*_Factory.*",
        "**/*_HiltModules*.*",
        "**/kotlin/*",
        "**/dagger/*"
    )

    val debugTree = fileTree("${layout.buildDirectory}/intermediates/javac/debug") {
        exclude(fileFilter)
    }

    sourceDirectories.setFrom(files("src/main/kotlin"))
    classDirectories.setFrom(debugTree)
    executionData.setFrom(files("${layout.buildDirectory}/jacoco/testDebugUnitTest.exec"))
}