import com.google.protobuf.gradle.id
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.protobufPlugin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hiltPlugin)
}

val key: String = Properties().let {
    it.load(project.rootProject.file("local.properties").inputStream())
    it.getProperty("key")
}

android {
    namespace = appConfig.versions.appId.get()
    compileSdk = appConfig.versions.targetSdk.get().toInt()

    defaultConfig {
        applicationId = appConfig.versions.appId.get()
        minSdk = appConfig.versions.minSdk.get().toInt()
        targetSdk = appConfig.versions.targetSdk.get().toInt()
        versionCode = appConfig.versions.versionCode.get().toInt()
        versionName = appConfig.versions.versionName.get()
        testInstrumentationRunner = appConfig.versions.testInstrumentationRunner.get()
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "API_KEY", "\"$key\"")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = appConfig.versions.kotlinCompilerExtensionVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.usecases)
    implementation(projects.data)
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
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlin.converter)
    implementation(libs.arrow.core)
    implementation(libs.datastore.core)
    implementation(libs.protobuf.javalite)
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
    implementation(libs.biometric)
    implementation(libs.play.services.location)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    implementation(libs.compose.android.permissions)

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.14")
    debugImplementation(libs.compose.ui.tooling)

}

kapt {
    correctErrorTypes = true
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