import com.davidluna.architectcoders2024.build_logic.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".main_ui")
}

dependencies {
    implementation(projects.navigation)
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.feature.splashUi)
    implementation(projects.feature.auth.authUi)
    implementation(projects.feature.media.mediaUi)
    implementation(projects.feature.videos.videosUi)
    implementation(libs.coilCompose)
}