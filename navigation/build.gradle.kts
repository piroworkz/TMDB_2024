import com.davidluna.architectcoders2024.build_logic.constants.Constants

plugins {
    alias(libs.plugins.androidLibraryConventionPlugin)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = Constants.NAMESPACE.plus(".navigation")
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreUi)
    implementation(libs.iconsExtended)
    implementation(libs.kotlinxSerializationJson)
    implementation(libs.navigation)
}