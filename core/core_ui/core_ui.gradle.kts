import com.davidluna.architectcoders2024.build_logic.constants.Constants.NAMESPACE

plugins {
    alias(libs.plugins.androidLibraryConventionPlugin)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = NAMESPACE.plus(".core_ui")
    buildFeatures {
        compose = true
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
    debugImplementation(libs.runtimeTracing)
    debugImplementation(libs.composeUiTooling)
    testImplementation(libs.junit)
    androidTestImplementation(libs.extJunit)
    androidTestImplementation(libs.espressoCore)

}