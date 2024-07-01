import com.davidluna.architectcoders2024.build_logic.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".auth_ui")
}

dependencies {
    implementation(projects.navigation)
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreUi)
    implementation(projects.feature.auth.authDomain)
    implementation(libs.arrowCore)
}