import com.davidluna.architectcoders2024.build_logic.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = Constants.NAMESPACE.plus(".navigation")
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreUi)
    implementation(libs.kotlinxSerializationJson)
}