plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
}

dependencies {
    api(projects.feature.splash)
    api(projects.core.coreUi)
    api(projects.core.coreDomain)
    api(projects.core.coreFramework)
    api(projects.feature.auth.authUi)
    api(projects.feature.auth.authDomain)
    api(projects.feature.auth.authFramework)
    api(projects.feature.media.mediaUi)
    api(projects.feature.media.mediaDomain)
    api(projects.feature.media.mediaFramework)
    api(projects.feature.videos.videosUi)
    api(projects.feature.videos.videosDomain)
    api(projects.feature.videos.videosFramework)
    implementation(libs.playServicesLocation)
    implementation(libs.datastoreCore)
}