import com.davidluna.architectcoders2024.build_logic.constants.Constants.KOTLIN_COMPILER_EXTENSION_VERSION
import com.davidluna.architectcoders2024.build_logic.constants.Constants.NAMESPACE

plugins {
    alias(libs.plugins.androidLibraryConventionPlugin)
}

android {
    namespace = NAMESPACE.plus(".core_ui")
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = KOTLIN_COMPILER_EXTENSION_VERSION
    }
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(platform(libs.composeBom))
    implementation(libs.composeUi)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeMaterial3)
    implementation(libs.iconsExtended)
    implementation(libs.coilCompose)
    implementation(libs.fragmentKtx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.extJunit)
    androidTestImplementation(libs.espressoCore)
}