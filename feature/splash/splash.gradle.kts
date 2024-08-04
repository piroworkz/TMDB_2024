import com.davidluna.architectcoders2024.convention.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".splash")
}

dependencies {
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    
    implementation(libs.composeAndroidPermissions)
    implementation(libs.biometric)
}