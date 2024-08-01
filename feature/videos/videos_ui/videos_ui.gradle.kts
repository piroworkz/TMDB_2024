import com.davidluna.architectcoders2024.convention.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".videos_ui")
}

dependencies {
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.feature.videos.videosDomain)
    implementation(projects.navigation)
    implementation(libs.pagingCompose)
    testImplementation(projects.testShared)
    testImplementation(projects.feature.videos.videosFramework)
    testImplementation(projects.core.coreFramework)
}
