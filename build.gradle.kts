import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.protobufPlugin) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hiltPlugin) apply false
    alias(libs.plugins.androidLibrary) apply false
}

subprojects {
    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}