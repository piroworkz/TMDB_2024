import com.davidluna.tmdb.convention.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".media_ui")
}

dependencies {
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.feature.media.mediaDomain)
    implementation(libs.pagingCompose)
    implementation(libs.coilCompose)
    testImplementation(projects.testShared)
    testImplementation(projects.feature.media.mediaFramework)
    testImplementation(projects.feature.videos.videosDomain)
    testImplementation(projects.feature.videos.videosFramework)
    testImplementation(projects.core.coreFramework)
}