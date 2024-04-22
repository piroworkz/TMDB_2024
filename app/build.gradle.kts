import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.protobufPlugin)
}

android {
    namespace = "com.davidluna.architectcoders2024"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.davidluna.architectcoders2024"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.compose.activity)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.compose.constraint.layout)
    implementation(libs.icons.extended)
    implementation(libs.coil.compose)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.retrofit)
    implementation(libs.okhttp.client)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.arrow.core)
    implementation(libs.datastore.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlin.converter)
    implementation(libs.protobuf.javalite)
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.media3.common)
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.exoplayer.dash)
    implementation(libs.media3.ui)

    debugImplementation(libs.compose.ui.tooling)

}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.12"
    }

    generateProtoTasks {
        all().forEach {
            it.builtins {
                id("java") {
                    option("lite")
                }

                id("kotlin") {
                    option("lite")
                }
            }
        }
    }
}