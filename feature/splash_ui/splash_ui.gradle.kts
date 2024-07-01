import com.davidluna.architectcoders2024.build_logic.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".splash_ui")
}

dependencies {
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.navigation)
    implementation(libs.composeAndroidPermissions)
    implementation(libs.biometric)
}