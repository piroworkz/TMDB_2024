package com.davidluna.tmdb.convention.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.davidluna.tmdb.convention.bundles.androidTestingBundle
import com.davidluna.tmdb.convention.bundles.composeUiBundle
import com.davidluna.tmdb.convention.bundles.koinAppBundle
import com.davidluna.tmdb.convention.bundles.ktorAndroidBundle
import com.davidluna.tmdb.convention.bundles.unitTestingBundle
import com.davidluna.tmdb.convention.constants.Constants
import com.davidluna.tmdb.convention.constants.Constants.COMPILE_SDK
import com.davidluna.tmdb.convention.constants.Constants.HILT_TEST_RUNNER
import com.davidluna.tmdb.convention.constants.Constants.KEY_ALIAS
import com.davidluna.tmdb.convention.constants.Constants.KEY_PASSWORD
import com.davidluna.tmdb.convention.constants.Constants.MIN_SDK
import com.davidluna.tmdb.convention.constants.Constants.NAMESPACE
import com.davidluna.tmdb.convention.constants.Constants.STORE_FILE
import com.davidluna.tmdb.convention.constants.Constants.STORE_PASSWORD
import com.davidluna.tmdb.convention.constants.Constants.TARGET_SDK
import com.davidluna.tmdb.convention.constants.Constants.VERSION_CODE
import com.davidluna.tmdb.convention.constants.Constants.VERSION_NAME
import com.davidluna.tmdb.convention.extensions.javaVersion
import com.davidluna.tmdb.convention.extensions.kotlin
import com.davidluna.tmdb.convention.extensions.plugins
import com.davidluna.tmdb.convention.helpers.alias
import com.davidluna.tmdb.convention.helpers.implementation
import com.davidluna.tmdb.convention.libs.androidApplication
import com.davidluna.tmdb.convention.libs.arrowCore
import com.davidluna.tmdb.convention.libs.coilCompose
import com.davidluna.tmdb.convention.libs.composeActivity
import com.davidluna.tmdb.convention.libs.composeCompiler
import com.davidluna.tmdb.convention.libs.composeNavigation
import com.davidluna.tmdb.convention.libs.kotlinAndroid
import com.davidluna.tmdb.convention.libs.kotlinxSerializationJson
import com.davidluna.tmdb.convention.libs.ksp
import com.davidluna.tmdb.convention.libs.libs
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.dependencies
import java.io.File

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {

        plugins {
            alias(libs.ksp)
            alias(libs.androidApplication)
            alias(libs.kotlinAndroid)
            alias(libs.composeCompiler)
        }

        android {
            namespace = NAMESPACE
            compileSdk = COMPILE_SDK

            defaultConfig {
                applicationId = NAMESPACE
                minSdk = MIN_SDK
                targetSdk = TARGET_SDK
                versionCode = VERSION_CODE
                versionName = VERSION_NAME
                testInstrumentationRunner = HILT_TEST_RUNNER
                vectorDrawables {
                    useSupportLibrary = true
                }

                @Suppress("UnstableApiUsage")
                externalNativeBuild {
                    ndkBuild {
                        arguments(
                            "${Constants.API_KEY}=${project.property(Constants.API_KEY)}",
                            "${Constants.BASE_URL}=${project.property(Constants.BASE_URL)}"
                        )
                    }
                }

                ndk {
                    abiFilters.addAll(Constants.ABI_FILTERS)
                }
            }

            externalNativeBuild {
                ndkBuild {
                    path = File("src/main/jni/Android.mk")
                }
            }

            signingConfigs {
                create("release") {
                    keyAlias =
                        System.getenv(KEY_ALIAS) ?: project.findProperty(KEY_ALIAS) as String
                    keyPassword =
                        System.getenv(KEY_PASSWORD) ?: project.findProperty(KEY_PASSWORD) as String
                    storeFile =
                        file(
                            System.getenv(STORE_FILE) ?: project.findProperty(STORE_FILE) as String
                        )
                    storePassword =
                        System.getenv(STORE_PASSWORD)
                            ?: project.findProperty(STORE_PASSWORD) as String
                }
            }

            buildTypes {
                release {
                    signingConfig = signingConfigs.getByName("release")
                    isMinifyEnabled = true
                    isShrinkResources = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }

                debug {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            compileOptions.apply {
                sourceCompatibility = Constants.JAVA_VERSION
                targetCompatibility = Constants.JAVA_VERSION
            }

            javaVersion

            buildFeatures {
                buildConfig = true
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

        }

        kotlin {
            jvmToolchain {
                languageVersion.set(JavaLanguageVersion.of(17))
            }
        }

        dependencies {
            composeUiBundle
            koinAppBundle
            implementation(libs.arrowCore)
            implementation(libs.composeActivity)
            implementation(libs.coilCompose)
            implementation(libs.composeNavigation)
            implementation(libs.kotlinxSerializationJson)
            ktorAndroidBundle
            unitTestingBundle
            androidTestingBundle
        }
    }

    private fun Project.android(config: Action<ApplicationExtension>): Unit =
        (this as ExtensionAware).extensions.configure("android", config)

}

